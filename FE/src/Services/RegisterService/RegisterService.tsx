import { RegisterCompanyDetails, RegisterStudentDetails, RegistrationType } from '../../Models/Register/Register';
import { getMultipartInstance } from "../Shared/AxiosService";


  export const register = async (registerData: RegisterStudentDetails | RegisterCompanyDetails): Promise<any> => {
    if(registerData.type === RegistrationType.STUDENT){
      return registerStudent(registerData as RegisterStudentDetails);
    }
    else if(registerData.type === RegistrationType.COMPANY){
      return registerCompany(registerData as RegisterCompanyDetails);
    }
    else{
      throw Error(`REGISRTATION FOR TYPE OF USER ${registerData.type}.`);
    }
  }

  export const registerStudent = async(registerData: RegisterStudentDetails): Promise<any> => {
    const formData = new FormData();
    formData.append('username', registerData.username);
    formData.append('email', registerData.email);
    formData.append('password', registerData.password);
    formData.append('avatarUrl', registerData.avatarUrl);
    formData.append('firstName', registerData.firstName);
    formData.append('lastName', registerData.lastName);
    formData.append('description', registerData.description);
    formData.append('dateOfBirth', registerData.dateOfBirth);

    return getMultipartInstance().post(
      'students/register',
      formData
    );
  };

  export const registerCompany = async (registerData: RegisterCompanyDetails): Promise<any> => {
    const formData = new FormData();
    formData.append('username', registerData.username);
    formData.append('email', registerData.email);
    formData.append('password', registerData.password);
    formData.append('avatarUrl', registerData.avatarUrl);
    formData.append('name', registerData.name);

    return getMultipartInstance().post(
      'companies/register',
      formData
    );
  };