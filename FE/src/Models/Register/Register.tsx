export enum RegistrationType {
  STUDENT, COMPANY
}


export class RegisterUserDetails {
  username: string;
  email: string;
  password: string;
  avatarUrl: string;
  type: RegistrationType;

  constructor(username: string, email: string, password: string, avatarUrl: string, type: RegistrationType) {
    this.type = type;
    this.username = username;
    this.email = email;
    this.password = password;
    this.avatarUrl = avatarUrl;
  }
}


export class RegisterCompanyDetails extends RegisterUserDetails {
  name: string;

  constructor(username: string, email: string, password: string, avatarUrl: string, name: string) {
    super(username, email, password, avatarUrl, RegistrationType.COMPANY);
    this.name = name;
  }
}

export interface RegisterState {
  type: RegistrationType | null,
  name: string,
  avatarUrl: string,
  email: string,
  username: string,
  password: string,
  confirmPassword: string,
  errorMessage: string,
  firstName: string,
  lastName: string,
  description: string,
  dateOfBirth: string,
  hasError: boolean,
  error: string,
}

export class RegisterStudentDetails extends RegisterUserDetails {
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  description: string;

  constructor(username: string, email: string, password: string, avatarUrl: string, firstName: string, lastName: string, dateOfBirth: string, description: string) {
    super(username, email, password, avatarUrl, RegistrationType.STUDENT);
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.description = description;
  }
}