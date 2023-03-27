import axios from "axios"

const axiosCall = axios.create({
    baseURL: "http://localhost:8080"
})

export default axiosCall
