import { useEffect, useState } from "react";
import { AuthManagerProps, EmptyProps } from "../../Models/interfaces";
import { logout } from "../../Services/AuthService/AuthService";
import { Navigate } from "react-router-dom";

const LogOutPage: React.FC<AuthManagerProps> = (props: AuthManagerProps) => {
    const [isLoggedOut, setIsLoggedOut] = useState<boolean>(false);

    useEffect(() => {
        logout()
            .then(() => {
                setIsLoggedOut(true)
                props.callback()
            })
            .catch(() => {
                setIsLoggedOut(true)
                props.callback()
            });
    }, [])

    if(isLoggedOut){
        return <Navigate to='/login' replace={true} />
    }

    return(
        <>
            Logging out...
        </>
    )
}

export default LogOutPage;