import { Navbar, Nav } from "rsuite";
import { Link } from "react-router";

export default function Navigation() {
  return (
    <Navbar>
      <Navbar.Brand href="/">MyApp</Navbar.Brand>
      <Nav>
        <Nav.Item as={Link} to="/">Home</Nav.Item>
        <Nav.Item as={Link} to="/phony">Phony</Nav.Item>
      </Nav>
    </Navbar>
  );
}
