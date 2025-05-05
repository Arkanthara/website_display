import { describe, it, expect } from "vitest";
import { hsvToRgb, generateSmoothColors } from "../components/colorUtils";

describe("hsvToRgb", () => {
  it("converts black (0, 0, 0) to rgba(0, 0, 0, a)", () => {
    expect(hsvToRgb(0, 0, 0, 1)).toBe("rgba(0, 0, 0, 1)");
  });

  it("converts white (0, 0, 100) to rgba(255, 255, 255, a)", () => {
    expect(hsvToRgb(0, 0, 100, 0.5)).toBe("rgba(255, 255, 255, 0.5)");
  });

  it("converts red (0, 100, 100) to rgba(255, 0, 0, a)", () => {
    expect(hsvToRgb(0, 100, 100, 0.8)).toBe("rgba(255, 0, 0, 0.8)");
  });

  it("converts green (120, 100, 100) to rgba(0, 255, 0, a)", () => {
    expect(hsvToRgb(120, 100, 100, 0.3)).toBe("rgba(0, 255, 0, 0.3)");
  });

  it("converts blue (240, 100, 100) to rgba(0, 0, 255, a)", () => {
    expect(hsvToRgb(240, 100, 100, 1)).toBe("rgba(0, 0, 255, 1)");
  });

  it("handles hue wraparound above 360 by default logic", () => {
    const color = hsvToRgb(420, 100, 100, 1); // Should wrap to a reddish color
    expect(color).toMatch(/^rgba\(\d+, \d+, \d+, 1\)$/);
  });
});

describe("generateSmoothColors", () => {
  it("generates consistent colors for bar index", () => {
    const result = generateSmoothColors(5, 2);
    expect(result).toHaveProperty("backgroundColor");
    expect(result).toHaveProperty("borderColor");
    expect(result.backgroundColor).toMatch(/^rgba\(/);
    expect(result.borderColor).toMatch(/^rgba\(/);
  });

  it("generates distinct colors for different indices", () => {
    const color1 = generateSmoothColors(10, 1);
    const color2 = generateSmoothColors(10, 2);
    expect(color1.backgroundColor).not.toBe(color2.backgroundColor);
    expect(color1.borderColor).not.toBe(color2.borderColor);
  });
});
