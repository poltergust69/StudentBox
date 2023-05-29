import { useEffect, useState } from "react";
import { logout } from "../../Services/AuthService/AuthService";
import { Navigate } from "react-router-dom";
import { AuthManagerProps } from "../../Models/Auth/AuthInterfaces";

const LogOutPage: React.FC<AuthManagerProps> = (props: AuthManagerProps) => {
  const [isLoggedOut, setIsLoggedOut] = useState<boolean>(false);

  useEffect(() => {
    logout()
      .then(() => {
        setIsLoggedOut(true);
        props.callback();
      })
      .catch(() => {
        setIsLoggedOut(true);
        props.callback();
      });
  }, []);

  if (isLoggedOut) {
    return <Navigate to="/login" replace={true} />;
  }

  return <>Logging out...</>;
};

export default LogOutPage;
