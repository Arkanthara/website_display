import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router";

import Home from "../pages/home.tsx";
import Power from "../pages/power.tsx";
import State from "../pages/state.tsx";

export default function Routing() {
  return (
    <Routes>
      <Route path="/home" element={<Home />} />
      <Route path="/power" element={<Power />} />
      <Route path="/state" element={<State />} />
    </Routes>
  );
}
