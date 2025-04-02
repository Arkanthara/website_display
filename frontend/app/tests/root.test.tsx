import { describe, test, expect, vi } from "vitest";
import { render, screen, cleanup } from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import { createRoutesStub, useRouteError } from "react-router";
import App from "../root";
import { ErrorBoundary } from "../root";
import { isRouteErrorResponse } from "react-router";

const Stub = createRoutesStub([
  {
    path: "/",
    Component: App,
  },
]);

describe("Test Root Component", () => {
  render(<Stub initialEntries={["/"]} />);
  test("Outlet component", () => {
    expect(screen.queryByTestId("outlet")).toBeNull();
  });
});

// vi.mock("react-router", () => ({
//   useRouteError: vi.fn(),
//   createRoutesStub: {path, factory} => {path, factory},
// }));

vi.mock(import("react-router"), async (importOriginal) => {
  const actual = await importOriginal();
  return {
    ...actual,
    // your mocked methods
    useRouteError: vi.fn(),
    isRouteErrorResponse: vi.fn(),
  };
});

describe("Test ErrorBoundary", () => {
  test("Test 404 message", () => {
    (useRouteError as vi.Mock).mockReturnValue({
      status: 404,
      statusText: "Not Found",
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(screen.getByText("404")).toBeInTheDocument();
    cleanup();
  });
  test("Test other Route message", () => {
    (useRouteError as vi.Mock).mockReturnValue({
      status: 500,
      statusText: "Internal Server Error",
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(screen.getByText("Internal Server Error")).toBeInTheDocument();
    cleanup();
    (useRouteError as vi.Mock).mockReturnValue({
      status: 500,
    });
    (isRouteErrorResponse as vi.Mock).mockReturnValue(true);
    render(<ErrorBoundary />);
    expect(
      screen.getByText("An unexpected error occurred.")
    ).toBeInTheDocument();
    cleanup();
  });
  test("Test if DevMode", () => {
    vi.stubEnv("DEV", true);
    (useRouteError as vi.Mock).mockReturnValue(
      new Error("You have been hacked !")
    );
    (isRouteErrorResponse as vi.Mock).mockReturnValue(false);
    render(<ErrorBoundary />);
    expect(screen.getByText("You have been hacked !")).toBeInTheDocument();
    cleanup();
  });
  test("Test if not DevMode", () => {
    vi.stubEnv("DEV", false);
    (useRouteError as vi.Mock).mockReturnValue(new Error("Hey Hey Hey !"));
    (isRouteErrorResponse as vi.Mock).mockReturnValue(false);
    render(<ErrorBoundary />);
    expect(
      screen.getByText("An unexpected error occurred.")
    ).toBeInTheDocument();
    cleanup();
  });
  vi.unstubAllEnvs();
  vi.resetModules();
});
