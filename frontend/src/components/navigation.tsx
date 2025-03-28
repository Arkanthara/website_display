import { Navbar, Nav, Container } from "react-bootstrap";
import { Link } from "react-router";
import React from "react";

export default function Navigation() {
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/home">
          EMS
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav" />
        <Nav className="me-auto">
          <Nav.Link as={Link} to="/home">
            Home
          </Nav.Link>
          <Nav.Link as={Link} to="/power">
            Power
          </Nav.Link>
          <Nav.Link as={Link} to="/state">
            State
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
