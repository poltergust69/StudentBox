import axios, { AxiosInstance } from "axios";
import { getToken } from "../AuthService/AuthService";

const baseInstance: AxiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
  headers: {
    "content-type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});

const multipartInstance: AxiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
  headers: {
    "content-type": "multipart/form-data",
    "Access-Control-Allow-Origin": "*",
  },
});

export const getBaseInstance = (): AxiosInstance => {
  return baseInstance;
};

export const getBaseAuthInstance = async (): Promise<AxiosInstance> => {
  return axios.create({
    baseURL: process.env.REACT_APP_BACKEND_URL,
    headers: {
      "content-type": "application/json",
      "Access-Control-Allow-Origin": "*",
      Auth: `Bearer ${await getToken()}`,
    },
  });
};

export const getMultipartInstance = (): AxiosInstance => {
  return multipartInstance;
};

export const getMultipartAuthInstance = async (): Promise<AxiosInstance> => {
  return axios.create({
    baseURL: process.env.REACT_APP_BACKEND_URL,
    headers: {
      "content-type": "multipart/form-data",
      "Access-Control-Allow-Origin": "*",
      Auth: `Bearer ${await getToken()}`,
    },
  });
};
