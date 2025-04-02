import { Outlet, ScrollRestoration } from "react-router";
import { Scripts } from "react-router";
import { Links } from "react-router";
import { Meta } from "react-router";
import Navigation from "./navigation";

export default function Layout() {
  return (
    <html lang="en">
      <meta charSet="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <Meta />
      <Links />
      <body>
        <Navigation />
        <Outlet />
        <ScrollRestoration />
        <Scripts />
      </body>
    </html>
  );
}
