import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { Button, Container, TextField, Typography } from '@mui/material';
import Grid from '@mui/material/Grid';
import { isLoggedIn } from "../../Services/AuthService/AuthService";
import { AuthManagerProps} from "../../Models/Auth/AuthInterfaces";
import { RegisterState } from "../../Models/Auth/Register";
import { Nav } from "react-bootstrap";
import registerService from "../../Services/RegisterService/RegisterService";
import { RegisterCompanyDetails } from "../../Models/Auth/Register";
import { RegisterUserDetails } from "../../Models/Auth/Register";
import { useNavigate } from "react-router-dom";
import { JSX } from "react/jsx-runtime";

const RegisterPage = (props: AuthManagerProps): JSX.Element => {

  const [state, setState] = useState<RegisterState>({
    name: "",
    avatarUrl: "https://example.com/avatar.jpg",
    user: "",
    email: "",
    username: "",
    password: "",
    confirmPassword: "",
    hasError: false,
    error: "",
    isRegistered: false,
    userType: "",
    errorMessage: ""
  });

  const navigate = useNavigate();
  const setEmail = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      email: e.target.value,
    });
  };

  const setUsername = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      username: e.target.value,
    });
  };

  const setPassword = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      password: e.target.value,
    });
  };

  const setConfirmPassword = (
    e: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setState({
      ...state,
      confirmPassword: e.target.value,
    });
  };

  const setName = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      name: e.target.value,
    });
  };

  const setUserType = (userType: string): void => {
    setState({
      ...state,
      userType: userType,
    });
  };

  const handleRegister = async () => {
    try {
      if (state.userType === "student") {
        const registerData: RegisterUserDetails = new RegisterUserDetails(
          state.username,
          state.email,
          state.password,
          state.avatarUrl,
        );
        console.log(registerData)
        await registerService.registerStudent(registerData);

      } else if (state.userType === "company") {
        const registerData: RegisterCompanyDetails = new RegisterCompanyDetails(
          state.username,
          state.email,
          state.password,
          state.avatarUrl,
          state.name
        );
        console.log(registerData)
        await registerService.registerCompany(registerData);
      }
      console.log("Registration successful");
      navigate("/login");
    } catch (error) {
      console.error("Registration failed:", error);
    }
  };


  useEffect(() => {
    isLoggedIn()
      .then((result) => {
        if (result) {
          setState({
            ...state,
            isRegistered: true,
          });
          props.callback();
        }
      })
      .catch(() => { });
  }, []);

  if (state.isRegistered) {
    return <Navigate to="/login" />;
  }

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      handleRegister();
    }
  };

  return (
    <Grid
      container
      alignContent="center"
      sx={{ height: "120vh" }}
      className="bg-primary bg-opacity-10"
    >
      <Grid item xs={12}>
        <Container className="col-8 d-flex justify-content-center flex-column p-0 align-self-center p-4 rounded bg-white shadow-lg py-5">
          <h1 className="text-center display-4 pb-3 mb-5 border-bottom border-2 col-6 mx-auto">
            Register
          </h1>
          <Grid
            container
            spacing={2}
            justifyItems="center"
            alignItems="center"
            direction="column"
            className="d-flex mx-auto p-0"
          >
            <Grid item className="col-6 d-flex justify-items-center p-0">
              <TextField
                fullWidth
                value={state.email}
                label="Email"
                onKeyDown={handleKeyPress}
                onChange={setEmail}
                error={state.hasError && state.email === ""}
                helperText={state.hasError && state.email === "" && "Email is required"}
              />
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.username}
                label="Username"
                onKeyDown={handleKeyPress}
                onChange={setUsername}
                error={state.hasError && state.username === ""}
                helperText={state.hasError && state.username === "" && "Username is required"}
              />
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.password}
                label="Password"
                type="password"
                onKeyDown={handleKeyPress}
                onChange={setPassword}
                error={state.hasError && state.password === ""}
                helperText={state.hasError && state.password === "" && "Password is required"}
              />
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.confirmPassword}
                label="Confirm Password"
                type="password"
                onKeyDown={handleKeyPress}
                onChange={setConfirmPassword}
                error={state.hasError && state.confirmPassword === ""}
                helperText={state.hasError && state.confirmPassword === "" && "Confirm Password is required"}
              />
            </Grid>
            <Grid>
              <Grid item>
                <Button
                  variant={state.userType === "student" ? "contained" : "outlined"}
                  onClick={() => setUserType("student")}
                >
                  Student
                </Button>
              </Grid>
              <Grid item>
                <Button
                  variant={state.userType === "company" ? "contained" : "outlined"}
                  onClick={() => setUserType("company")}
                >
                  Company
                </Button>
              </Grid>
            </Grid>
            {state.userType === "company" && (
              <Grid item className="col-6 p-0 mt-3">
                <TextField
                  fullWidth
                  value={state.name}
                  label="Company Name"
                  onKeyDown={handleKeyPress}
                  onChange={setName}
                />
              </Grid>
            )}
            <Grid item className="col-4 p-0 mt-3">
              <Button
                onClick={handleRegister}
                fullWidth
                variant="contained"
                size="small"
              >
                <span className="text-transform-none" style={{ fontSize: "2rem" }}>
                  Register
                </span>
              </Button>
              <Nav.Link href="/login">
                Already have an account? Log in
              </Nav.Link>
              {state.hasError && state.errorMessage && (
                <Typography variant="body2" color="error">
                  {state.errorMessage}
                </Typography>
              )}
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Grid>
  );
  
              }

export default RegisterPage;
