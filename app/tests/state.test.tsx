import { screen, render } from "@testing-library/react";
import { describe, test, expect } from "vitest";
import "@testing-library/jest-dom/vitest";
import State from "../routes/state";

describe("Test State component", () => {
  render(<State />);

  test("Heading exists", () => {
    expect(screen.getByRole("heading", { name: /state/i })).toBeInTheDocument();
  });
});
