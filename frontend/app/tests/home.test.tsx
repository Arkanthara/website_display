import { render, screen } from "@testing-library/react";
import Home from "../routes/home";
import "@testing-library/jest-dom/vitest";
import { describe, test, expect } from "vitest";

describe("Test of Homepage", () => {
  test("Render Homepage", () => {
    render(<Home />);

    expect(screen.getByRole("heading", { name: /home/i })).toBeInTheDocument();

    const paragraphs = screen.getAllByText(
      (_, element) => element?.tagName.toLowerCase() === "p"
    );
    expect(paragraphs.length).toBe(2);
  });
});
