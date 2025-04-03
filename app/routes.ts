import {
  type RouteConfig,
  index,
  route,
  layout,
} from "@react-router/dev/routes";

export default [
  layout("./components/layout.tsx", [
    index("./routes/home.tsx"),
    route("/power", "./routes/power.tsx"),
    route("/state", "./routes/state.tsx"),
  ]),
] satisfies RouteConfig;
