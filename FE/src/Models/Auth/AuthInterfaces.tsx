export interface AuthManagerProps {
  callback: () => void;
}

export interface AuthData {
  id: string;
  username: string;
  role: RoleModel;
  email: string;
  firstName: string;
  lastName: string;
}

export interface RoleModel {
  id: string;
  name: string;
  authority: string;
}

export interface LoginState {
  user: string;
  password: string;
  hasError: boolean;
  error: string | null;
  isLoggedIn: boolean;
}

export interface UserModel {
  id: string;
  username: string;
  avatarUrl: string;
}
