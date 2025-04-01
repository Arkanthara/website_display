import { describe, test, expect } from "vitest";

import routeconfig from "../routes";

import { type RouteConfig, index, route } from "@react-router/dev/routes";

describe("Routes test", () => {
  test("Should have correct route number", () => {
    expect(routeconfig).toBeInstanceOf(Array);
    expect(routeconfig).toHaveLength(3);
  });
  const [home, power, state] = routeconfig;
  test("Index route exist", () => {
    expect(home).toEqual(index("./routes/home.tsx"));
    expect(power).toEqual(route("/power", "./routes/power.tsx"));
    expect(state).toEqual(route("/state", "./routes/state.tsx"));
  });
  test("Our route config satisfy RouteConfig type", () => {
    const test_type: RouteConfig = routeconfig;
    expect(test_type).toBeDefined();
  });
});
