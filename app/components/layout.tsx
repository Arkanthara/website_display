import { Outlet, ScrollRestoration, Scripts, Links, Meta } from "react-router";
import Navigation from "./navigation";
import { CustomProvider, Container } from "rsuite";
import "rsuite/dist/rsuite.min.css";

export default function Layout() {
  return (
    <html lang="en">
      <head>
        <title>Gnyahaha</title>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Meta />
        <Links />
      </head>
      <body>
        <CustomProvider theme="dark">
          <Container>
            <Navigation />
            <Outlet />
            <ScrollRestoration />
            <Scripts />
          </Container>
        </CustomProvider>
      </body>
    </html>
  );
}
