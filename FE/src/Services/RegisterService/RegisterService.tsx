import axios, { AxiosError } from 'axios';
import { RegisterCompanyDetails } from '../../Models/Auth/Register';
import { RegisterUserDetails } from '../../Models/Auth/Register';
import { getMultipartInstance } from "../Shared/AxiosService";

const registerService = {
  registerStudent: async (registerData: RegisterUserDetails) => {
    try {
      const formData = new FormData();
      formData.append('username', registerData.username);
      formData.append('email', registerData.email);
      formData.append('password', registerData.password);
      formData.append('avatarUrl', registerData.avatarUrl);

      const response = await getMultipartInstance().post(
        'http://localhost:8080/students/register',
        formData
      );

      return response.data;
    } catch (error) {
      throw error;
    }
  },

  registerCompany: async (registerData: RegisterCompanyDetails) => {
    try {
      const formData = new FormData();
      formData.append('username', registerData.username);
      formData.append('email', registerData.email);
      formData.append('password', registerData.password);
      formData.append('avatarUrl', registerData.avatarUrl);
      formData.append('name', registerData.name);

      const response = await getMultipartInstance().post(
        'http://localhost:8080/companies/register',
        formData
      );

      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default registerService;