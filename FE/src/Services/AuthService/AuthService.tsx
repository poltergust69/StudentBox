import { AxiosError } from "axios";
import MultipartURL from "../Shared/MultipartURL";
import jwtDecode, { JwtPayload } from "jwt-decode";

export const login = async (user: string, password: string): Promise<string> => {
    const formData = new FormData();
    formData.append('user', user)
    formData.append('password', password)

    return MultipartURL.post("users/login",  formData)
        .then((response) => {
            localStorage.setItem('accessToken', response.data.accessToken)
            localStorage.setItem('refreshToken', response.data.refreshToken)

            return response.data.accessToken
        })
        .catch((error: AxiosError) => {
            throw error
        })
};

export const requestTokenRefresh = async (refreshToken: string): Promise<string> => {
    const formData = new FormData();
    formData.append('refreshToken', refreshToken)

    return await MultipartURL.post("users/refresh-token",  formData)
        .then((response) => {
            localStorage.setItem('accessToken', response.data.accessToken)
            localStorage.setItem('refreshToken', response.data.refreshToken)

            return response.data.accessToken
        })
        .catch((error: AxiosError) => {
            throw error
        })
};


export const getToken = async(): Promise<string> => {
    const accessToken = localStorage.getItem('accessToken')

    if(!accessToken){
        throw new Error('User is not logged in!');
    }

    const decodedToken = jwtDecode<JwtPayload>(accessToken)

    if(!decodedToken.exp || decodedToken.exp < ((new Date()).getTime()/1000)){
        const refreshToken = localStorage.getItem('refreshToken')

        if(!refreshToken){
            throw new Error('User is not logged in!');
        }
        
        const decodedRefreshToken = jwtDecode<JwtPayload>(refreshToken)

        if(!decodedRefreshToken.exp || decodedRefreshToken.exp < ((new Date()).getTime()/1000)){
            throw new Error('User is not logged in!');
        }

        return await requestTokenRefresh(refreshToken)
    }

    return accessToken;
}

export const isLoggedIn = async (): Promise<boolean> => {
    try{
        const accessToken = await getToken();

        if(!accessToken){
            return false;
        }

        const decodedToken = jwtDecode<JwtPayload>(accessToken)

        if(decodedToken.exp && decodedToken.exp >= ((new Date()).getTime()/1000)){
            return true;
        }

        return false;
    }
    catch{
        return false;
    }
}

export const logout = async (): Promise<boolean> => {
    try{
        const accessToken = await getToken();

        if(!accessToken){
            return true;
        }

        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
        return true;
    }
    catch{
        return true;
    }
}