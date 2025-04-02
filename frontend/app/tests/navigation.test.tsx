import { expect, describe, test } from "vitest";
import "@testing-library/jest-dom/vitest";
import { screen, render } from "@testing-library/react";
import Navigation from "../components/navigation";
import { BrowserRouter } from "react-router";

describe("Navigation bar test", () => {
  render(
    <BrowserRouter>
      <Navigation />
    </BrowserRouter>
  );
  test("Navigation bar exist", () => {
    expect(screen.getByRole("navigation")).toBeInTheDocument();
  });
});
