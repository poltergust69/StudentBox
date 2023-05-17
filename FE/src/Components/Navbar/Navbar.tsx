import { useEffect, useState } from "react";
import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { Avatar, Button } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import LockIcon from "@mui/icons-material/Lock";
import KeyIcon from "@mui/icons-material/Key";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { NavbarProps } from "../../Models/Navbar/NavbarInterfaces";
import { isLoggedIn } from "../../Services/AuthService/AuthService";
import {
  generateStringAvatar,
  getUserAvatar,
  getUserFullName,
  getUserUsername,
} from "../../Services/HelperService/HelperService";

const NavbarLayout: React.FC<NavbarProps> = (props: NavbarProps) => {
  const [isUserLoggedIn, setIsLoggedIn] = useState<boolean>(false);

  useEffect(() => {
    isLoggedIn()
      .then((result) => {
        setIsLoggedIn(result);
      })
      .catch(() => setIsLoggedIn(false));
  }, [props.changeCounter]);

  const isLinkActive = (path: string): boolean => {
    return path === window.location.pathname;
  };

  return (
    <Navbar bg="success" variant="dark" fixed={"top"}>
      <Container>
        <Navbar.Brand href="/">StudentBox</Navbar.Brand>
        <Nav className="me-auto w-100 d-flex align-items-center">
          <Nav.Link href="/forum" active={isLinkActive("/forum")}>
            Forum
          </Nav.Link>
          {!isUserLoggedIn ? (
            <Nav.Link href="/login" className="d-block ms-auto me-2">
              <Button
                startIcon={<KeyIcon className="keyIcon-rotatedFlipped" />}
                variant="contained"
                className="text-transform-none"
              >
                Log in
              </Button>
            </Nav.Link>
          ) : (
            <NavDropdown
              className="ms-auto dropdown-nav-toggler"
              title={
                <div className="d-flex rounded bg-dark bg-opacity-75 border border-1 border-light justify-items-around align-items-center px-3 py-1 justify-self-end dropdown-nav-toggler">
                  {getUserAvatar() ? (
                    <Avatar
                      src={getUserAvatar() ?? ""}
                      className="me-2 bg-light border border-2 border-light"
                    />
                  ) : (
                    <Avatar
                      {...generateStringAvatar(getUserFullName(), undefined)}
                      className="me-2"
                    />
                  )}
                  <span className="text-white">{getUserUsername()}</span>
                  <ExpandMoreIcon />
                </div>
              }
            >
              <NavDropdown.Item
                href="/profile"
                className="navdropdown-item d-flex align-items-center"
              >
                <PersonIcon fontSize={"small"} className="me-2" />
                Profile
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item
                href="/logout"
                className="navdropdown-item d-flex align-items-center"
              >
                <LockIcon fontSize={"small"} className="me-2" /> Log out
              </NavDropdown.Item>
            </NavDropdown>
          )}
        </Nav>
      </Container>
    </Navbar>
  );
};

export default NavbarLayout;
