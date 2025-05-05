import { layout, index, route } from "@react-router/dev/routes";

export default [
  layout("./components/layout.tsx", [
    index("./routes/home.tsx"),
    route("/phony", "./routes/phony.tsx"),
  ]),
];
