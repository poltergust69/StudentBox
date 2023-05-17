import { Button, Container, Grid, TextField, Tooltip } from "@mui/material";
import { useEffect, useState } from "react";
import { isLoggedIn, login } from "../../Services/AuthService/AuthService";
import { Navigate } from "react-router-dom";
import { AuthManagerProps, LoginState } from "../../Models/Auth/AuthInterfaces";

const LoginPage: React.FC<AuthManagerProps> = (props: AuthManagerProps) => {
  const [state, setState] = useState<LoginState>({
    user: "",
    password: "",
    hasError: false,
    error: null,
    isLoggedIn: false,
  });

  const setPassword = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      password: e.target.value,
    });
  };

  const setUsername = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      user: e.target.value,
    });
  };

  const handleLogin = (): void => {
    login(state.user, state.password).then(() => {
      setState({
        ...state,
        isLoggedIn: true,
      });
      props.callback();
    });
  };

  useEffect(() => {
    isLoggedIn()
      .then((result) => {
        setState({
          ...state,
          isLoggedIn: result,
        });
        props.callback();
      })
      .catch(() => {
        setState({
          ...state,
          isLoggedIn: false,
        });
        props.callback();
      });
  }, []);

  if (state.isLoggedIn) {
    return <Navigate to="/" />;
  }

  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key == "Enter") {
      handleLogin();
    }
  };

  return (
    <Grid
      container
      alignContent={"center"}
      sx={{ height: "100vh" }}
      className="bg-primary bg-opacity-10"
    >
      <Grid item xs={12}>
        <Container className="col-8 d-flex justify-content-center flex-column p-0 align-self-center p-4 rounded bg-white shadow-lg py-5">
          <h1 className="text-center display-4 pb-3 mb-5 border-bottom border-2 col-6 mx-auto">
            Log In
          </h1>
          <Grid
            container
            spacing={2}
            justifyItems={"center"}
            alignItems={"center"}
            direction={"column"}
            className="d-flex mx-auto p-0"
          >
            <Grid item className="col-6 d-flex justify-items-center p-0">
              <TextField
                fullWidth={true}
                value={state.user}
                label="Username or Email"
                onKeyDown={handleKeyPress}
                onChange={setUsername}
              />
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth={true}
                value={state.password}
                label="Password"
                type={"password"}
                onKeyDown={handleKeyPress}
                onChange={setPassword}
              />
            </Grid>
            <Grid item className="col-6">
              <Grid container>
                <Grid item xs={8}></Grid>
                <Grid item xs={4}>
                  <Tooltip
                    title={
                      "This functionality is currently disabled, please try again later."
                    }
                  >
                    <Button
                      fullWidth
                      size={"small"}
                      variant={"outlined"}
                      className="border-secondary text-secondary text-transform-none"
                      disableRipple
                    >
                      Forgot Password?
                    </Button>
                  </Tooltip>
                </Grid>
              </Grid>
            </Grid>
            <Grid item className="col-4 p-0 mt-3">
              <Button
                onClick={handleLogin}
                fullWidth
                variant={"contained"}
                size={"small"}
              >
                <span
                  className="text-transform-none"
                  style={{ fontSize: "2rem" }}
                >
                  Log In
                </span>
              </Button>
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Grid>
  );
};

export default LoginPage;
