export class RegisterUserDetails {
    username: string;
    email: string;
    password: string;
    avatarUrl: string;
  
    constructor(username: string, email: string, password: string, avatarUrl: string) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.avatarUrl = avatarUrl;
    }
  }
  

  export class RegisterCompanyDetails extends RegisterUserDetails {
    name: string;
  
    constructor(username: string, email: string, password: string, avatarUrl: string, name: string) {
      super(username, email, password, avatarUrl);
      this.name = name;
    }
  }

  export interface RegisterState{
    name: string;
    avatarUrl: string;
    user: string;
    email: string;
    username: string;
    password: string;
    confirmPassword: string;
    hasError: boolean;
    error: string;
    isRegistered: boolean;
    userType: string;
    errorMessage: string;
  }