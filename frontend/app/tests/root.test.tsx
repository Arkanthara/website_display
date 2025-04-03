import { describe, test, expect, vi } from "vitest";
import { render, screen, cleanup } from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import {
  createRoutesStub,
  useRouteError,
  isRouteErrorResponse,
} from "react-router";
import App, { ErrorBoundary, links } from "../root";

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

describe("links function", () => {
  test("should return the correct links array", () => {
    const result = links();

    // Test if the result is an array
    expect(Array.isArray(result)).toBe(true);

    // Test if the array contains the expected objects
    expect(result).toHaveLength(2); // We expect 2 elements in the array

    // Test the first link object
    expect(result[0]).toEqual({
      rel: "preconnect",
      href: "https://fonts.googleapis.com",
    });

    // Test the second link object
    expect(result[1]).toEqual({
      rel: "preconnect",
      href: "https://fonts.gstatic.com",
      crossOrigin: "anonymous",
    });
  });
});
