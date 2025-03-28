import React from "react";
import Navigation from "./components/navigation.tsx";
import Routing from "./components/routing.tsx";
import { BrowserRouter } from "react-router";

export default function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routing />
      </BrowserRouter>
    </>
  );
}
