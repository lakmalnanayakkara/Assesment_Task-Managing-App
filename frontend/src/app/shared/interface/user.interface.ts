export interface UserSignInDetails {
  username: string;
  password: string;
}

export interface UserSignUpDetails extends UserSignInDetails {
  role: string;
}
