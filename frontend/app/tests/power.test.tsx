import Power from "../routes/power";
import { screen, render } from "@testing-library/react";
import "@testing-library/jest-dom/vitest";
import { describe, expect, test } from "vitest";

describe("Test of Power page", () => {
  render(<Power />);
  test("Test of title of the page", () => {
    expect(screen.getByRole("heading", { name: /power/i })).toBeInTheDocument();
  });
});
