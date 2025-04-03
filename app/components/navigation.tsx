import { NavLink } from "react-router";

export default function Navigation() {
  return (
    <nav>
      <ul>
        <li>
          <NavLink to="/" end>
            Home
          </NavLink>
        </li>
        <li>
          <NavLink to="/power" end>
            Power
          </NavLink>
        </li>
        <li>
          <NavLink to="/state" end>
            State
          </NavLink>
        </li>
      </ul>
    </nav>
  );
}
