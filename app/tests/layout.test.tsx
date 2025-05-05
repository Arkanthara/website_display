import Layout from "../components/layout";
import { screen, render } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import "@testing-library/jest-dom/vitest";
import { createRoutesStub } from "react-router";

describe("Test Layout", () => {
  const Stub = createRoutesStub([
    {
      path: "/",
      Component: Layout,
    },
  ]);
  render(<Stub initialEntries={["/"]} />);
  test("Navigation bar is in the document", () => {
    expect(screen.getByRole("navigation")).toBeInTheDocument();
  });
});
