import axios from "axios";

export default axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
  headers: {
    "content-type": "multipart/form-data",
    "Access-Control-Allow-Origin": "*",
  },
});