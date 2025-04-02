import { describe, test, expect, vi } from "vitest";

// Mock the route functions from "@react-router/dev/routes"
vi.mock("@react-router/dev/routes", () => ({
  // Mock the `index`, `route`, and `layout` functions
  index: vi.fn((path: string) => `Index route for ${path}`),
  route: vi.fn(
    (path: string, file: string) => `Route for ${path} with file ${file}`
  ),
  layout: vi.fn((file: string, children: any) => ({
    file,
    children,
  })),
}));

// Now import routeconfig after mocking
import routeconfig from "../routes";
import type { RouteConfig } from "@react-router/dev/routes";

describe("Routes test with mocks", () => {
  test("Should have correct route number", () => {
    // Check that routeconfig is an array
    expect(routeconfig).toBeInstanceOf(Array);

    // Check that the first item in routeconfig has 1 layout and 3 children
    expect(routeconfig[0].children).toHaveLength(3);
  });

  test("Index route exists", () => {
    // Destructure the children routes from the first layout
    const layoutConfig = routeconfig[0];
    if (layoutConfig.children) {
      const [home, power, state] = layoutConfig.children;

      // Check that the home route is mocked correctly as an index route
      expect(home).toBe("Index route for ./routes/home.tsx");

      // Check the power and state routes with their correct paths and file names
      expect(power).toBe("Route for /power with file ./routes/power.tsx");
      expect(state).toBe("Route for /state with file ./routes/state.tsx");
    } else {
      throw new Error("No Children in layoutConfig");
    }
  });

  test("Our route config satisfies RouteConfig type", () => {
    // TypeScript will handle type checking at compile time
    const test_type: RouteConfig = routeconfig;
    expect(test_type).toBeDefined();
  });
});

// describe("Routes test", () => {
//   const config = routeconfig[0];
//   const [home, power, state] = config.children;
//   test("Should have correct route number", () => {
//     expect(config).toBeInstanceOf(Array);
//     expect(config.children).toHaveLength(3);
//   });
//   test("Index route exist", () => {
//     expect(home).toEqual(index("./routes/home.tsx"));
//     expect(power).toEqual(route("/power", "./routes/power.tsx"));
//     expect(state).toEqual(route("/state", "./routes/state.tsx"));
//   });
//   test("Our route config satisfy RouteConfig type", () => {
//     const test_type: RouteConfig = routeconfig;
//     expect(test_type).toBeDefined();
//   });
// });
