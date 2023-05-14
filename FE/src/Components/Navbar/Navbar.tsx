import { Container, Nav, Navbar } from "react-bootstrap";
import { NavbarProps } from "../../Models/interfaces";
import { isLoggedIn } from "../../Services/AuthService/AuthService";
import { useEffect, useState } from "react";
import { Button } from "@mui/material";
import LockOpenIcon from '@mui/icons-material/LockOpen';
import LockIcon from '@mui/icons-material/Lock';

const NavbarLayout = (props: NavbarProps) => {
    const [isUserLoggedIn, setIsLoggedIn] = useState<boolean>(false);

    useEffect(() => {
      isLoggedIn()
        .then((result) => {setIsLoggedIn(result)})
        .catch(() => setIsLoggedIn(false))
    }, [props.changeCounter])

    const isLinkActive = (path: string):boolean => {
        return path === window.location.pathname;
    }

    return (
      <Navbar bg="dark" variant="dark" fixed={'top'}>
        <Container>
          <Navbar.Brand href="/">StudentBox</Navbar.Brand>
          <Nav className="me-auto w-100 d-flex align-items-center">
            {
              !isUserLoggedIn 
              ? <Nav.Link href="/login" className="d-block ms-auto me-2">
                <Button startIcon={<LockOpenIcon/>} variant="contained" size={'small'} className="text-transform-none">Log in</Button>
              </Nav.Link>
              : <Nav.Link href="/logout" className="d-block ms-auto me-2">
              <Button startIcon={<LockIcon/>} variant="outlined" size={'small'} className="text-transform-none">Log out</Button>
            </Nav.Link>
            }
          </Nav>
        </Container>
      </Navbar>
    )
}

export default NavbarLayout;