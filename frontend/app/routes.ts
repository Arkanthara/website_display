import { type RouteConfig, index, route } from "@react-router/dev/routes";

export default [
  index("routes/home.tsx"),
  route("/power", "routes/power.tsx"),
  route("/state", "routes/state.tsx"),
] satisfies RouteConfig;
