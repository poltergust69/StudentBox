import axios from "../custom-axios/axios";

const UserService = {

    loginUser: (authRequestModel) => axios.post("/users/login",{
        "authRequestModel":authRequestModel
    }),
    fetchAllRoles: () =>  axios.get("/users/roles"),

    refreshToken: (refreshToken) => axios.post("/users/refresh-token",{
        "refreshToken":refreshToken
    }),


    forgotPassword: (email) => axios.put("/users/forgot-password",{
        "email":email
    }),

    resetPassword: (resetPasswordModel) => axios.post("/users/reset-password",{
        "resetPasswordModel":resetPasswordModel
        })

}

export default UserService
