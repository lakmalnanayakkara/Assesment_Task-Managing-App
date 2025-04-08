import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  UserSignUpDetails,
  UserSignInDetails,
} from '../shared/interface/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/v1/user';
  constructor(private http: HttpClient) {}

  userSignUp(userSignUpDetails: UserSignUpDetails): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-up`, userSignUpDetails);
  }

  userSignIn(userSignInDetails: UserSignInDetails): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/sign-in`, userSignInDetails);
  }
}
