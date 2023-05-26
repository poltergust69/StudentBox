import React, { useState } from "react";
import { Alert, Button, Container, TextField, Typography } from '@mui/material';
import Grid from '@mui/material/Grid';
import { RegisterState, RegisterStudentDetails, RegistrationType } from "../../Models/Register/Register";
import { Nav } from "react-bootstrap";
import { register } from "../../Services/RegisterService/RegisterService";
import { RegisterCompanyDetails } from "../../Models/Register/Register";
import { EmptyProps } from "../../Models/Shared/SharedInterfaces";
import { Link, useNavigate } from "react-router-dom";

const RegisterPage: React.FC<EmptyProps> = (props: EmptyProps) => {

  const [state, setState] = useState<RegisterState>({
    name: "",
    avatarUrl: "https://example.com/avatar.jpg",
    email: "",
    username: "",
    password: "",
    confirmPassword: "",
    hasError: false,
    description: "",
    dateOfBirth: "",
    firstName: "",
    lastName: "",
    error: "",
    type: null,
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

  const setFirstName = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      firstName: e.target.value,
    });
  };

  const setLastName = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      lastName: e.target.value,
    });
  };

  const setDescription = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      description: e.target.value,
    });
  };

  const setDateOfBirth = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setState({
      ...state,
      dateOfBirth: e.target.value,
    });
  };

  const setUserType = (type: RegistrationType): void => {
    setState((state) => (
      {
        ...state,
        type,
      }
    ));
  };

  const handleRegister = async () => {
    let registerData: RegisterStudentDetails | RegisterCompanyDetails;

    if (state.type === RegistrationType.STUDENT) {
      registerData = new RegisterStudentDetails(
        state.username,
        state.email,
        state.password,
        state.avatarUrl,
        state.firstName,
        state.lastName,
        state.dateOfBirth,
        state.description
      );
    }
    else if (state.type === RegistrationType.COMPANY) {
      registerData = new RegisterCompanyDetails(
        state.username,
        state.email,
        state.password,
        state.avatarUrl,
        state.name
      );
    }
    else {
      throw Error(`REGISRTATION FOR TYPE OF USER ${state.type}.`);
    }
    register(registerData)
      .then(() => {
        navigate('/login')
      })
      .catch((error) => {
        setState((state) => ({
          ...state,
          hasError: true,
          error: error.response.data.message
        }))
      })
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
          <Grid container xs={6} direction={'row'} className="mb-5 mx-auto">
              <Grid item xs={6}>
                <Button
                  fullWidth
                  variant={state.type === RegistrationType.STUDENT ? "contained" : "outlined"}
                  color={'success'}
                  size={'large'}
                  onClick={() => setUserType(RegistrationType.STUDENT)}
                  className={`${state.type !== RegistrationType.STUDENT ? 'text-secondary border-secondary opacity-75' : ''} rounded-0 rounded-start`}>
                  Student
                </Button>
              </Grid>
              <Grid item xs={6}>
                <Button
                  fullWidth
                  variant={state.type === RegistrationType.COMPANY ? "contained" : "outlined"}
                  color={'success'}
                  size={'large'}
                  onClick={() => setUserType(RegistrationType.COMPANY)}
                  className={`${state.type !== RegistrationType.COMPANY ? 'text-secondary border-secondary opacity-75' : ''} rounded-0 rounded-end`}>
                  Company
                </Button>
              </Grid>
            </Grid>
          <Grid
            container
            spacing={2}
            justifyItems="center"
            alignItems="center"
            direction="column"
            className="d-flex mx-auto p-0"
          >
            <Grid item className="col-9 d-flex justify-items-center p-0">
              {
                state.hasError && (
                  <Alert severity={'error'} className="mx-auto my-2 p-3 col-12">{state.error}</Alert>
                )
              }
            </Grid>
            <Grid item className="col-6 d-flex justify-items-center p-0">
              <TextField
                fullWidth
                value={state.email}
                label="Email"
                onChange={setEmail}/>
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.username}
                label="Username"
                onChange={setUsername}/>
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.password}
                label="Password"
                type="password"
                onChange={setPassword}/>
            </Grid>
            <Grid item className="col-6 p-0 mt-3">
              <TextField
                fullWidth
                value={state.confirmPassword}
                label="Confirm Password"
                type="password"
                onChange={setConfirmPassword}/>
            </Grid>

            {
              state.type === RegistrationType.COMPANY &&
              (
                <Grid item className="col-6 p-0 mt-3">
                  <TextField
                    fullWidth
                    value={state.name}
                    label="Company Name"
                    onChange={setName}
                  />
                </Grid>
              )
            }

            {
              state.type === RegistrationType.STUDENT &&
              (
                <>
                  <Grid item className="col-6 p-0 mt-3">
                    <TextField
                      fullWidth
                      value={state.firstName}
                      label="Name"
                      onChange={setFirstName}
                    />
                  </Grid>
                  <Grid item className="col-6 p-0 mt-3">
                    <TextField
                      fullWidth
                      value={state.lastName}
                      label="Last Name"
                      onChange={setLastName}
                    />
                  </Grid>
                  <Grid item className="col-6 p-0 mt-3">
                    <TextField
                      fullWidth
                      value={state.description}
                      label="Description"
                      onChange={setDescription}
                    />
                  </Grid>
                  <Grid item className="col-6 p-0 mt-3">
                    <TextField
                      fullWidth
                      value={state.dateOfBirth}
                      label="Date of Birth"
                      type={'date'}
                      onChange={setDateOfBirth}
                    />
                  </Grid>
                </>
              )
            }

            <Grid item className="my-auto col-4">
              <Button
                disabled={state.type === null}
                onClick={handleRegister}
                fullWidth
                variant="contained"
                size="large"
              >
                <span className="text-transform-none" style={{ fontSize: "2rem" }}>
                  Register
                </span>
              </Button>
            </Grid>
            <Grid item justifyItems={'end'}>
              Already have an account? 
              <Link to="/login">
                 Log in
              </Link>
            </Grid>
          </Grid>
        </Container>
      </Grid>
    </Grid>
  );

}

export default RegisterPage;
