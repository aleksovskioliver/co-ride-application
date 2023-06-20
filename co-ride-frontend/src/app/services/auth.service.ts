import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap, throwError } from 'rxjs';
import { AuthenticationRequest } from '../models/AuthenticationRequest';
import * as moment from 'moment';
import { AuthenticationResponse } from '../models/AuthenticationResponse';
import { UserDTO } from '../models/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  registerUser(user: UserDTO) {
    return this.http.post(`${this.url}/api/user/create`, user)
  }

  loginUser(req: AuthenticationRequest) {
    return this.http.post<AuthenticationResponse>(`${this.url}/authenticate`, req).pipe(
      tap(it => this.setSession(it)),
      catchError(error => throwError(() => new Error(error.error)))
    )
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  private setSession(authResult: AuthenticationResponse) {
    const expiresAt = moment(authResult.expiresIn)

    localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()))
    localStorage.setItem('id_token', authResult.jwt);
  }

  private getExpiration() {
    const expiration = localStorage.getItem("expires_at")
    if (expiration != null) {
      const expiresAt = JSON.parse(expiration);
      return moment(expiresAt);
    }
    return null
  }
}
