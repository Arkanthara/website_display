import { Outlet, ScrollRestoration, Scripts, Links, Meta } from "react-router";
import Navigation from "./navigation";

export default function Layout() {
  return (
    <html lang="en">
      <head>
        <title>EMS Prophylaxis</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Meta />
        <Links />
      </head>
      <body>
        <Navigation />
        <Outlet />
        <ScrollRestoration />
        <Scripts />
      </body>
    </html>
  );
}
