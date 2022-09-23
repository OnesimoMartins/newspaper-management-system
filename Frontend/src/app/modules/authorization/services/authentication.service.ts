import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { Login } from '../models/login.model';
import {map,Observable,of} from 'rxjs'
import { User } from 'src/app/core/models/authUser.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  private url = environment.tokenRequestUrl;

  private  headers = new HttpHeaders(
    {'Content-Type':'application/x-www-form-urlencoded',
    'Authorization':'Basic RlJPTlRFTkQ6U0VDUkVU'}
    );

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) { }

  getCurrentUser():User{
   const payload= this.jwtHelper.decodeToken(this.getToken())
   const user= new User()

   user.authorities=payload.authorities
   user.newspaper_id=payload.newspaper_id
   user.user_name=payload.user_name
   user.user_id=payload.user_id

   return user;
  }

  login(login: Login): Observable<boolean> {


    let body=`username=${login.email}&password=${login.password}&grant_type=password`

   return  this.http .post<any>(this.url, body, {
        headers:this.headers
      }).pipe(map(response=>{
        this.storeToken(response.access_token);
        return true;
      }))

  }

  refreshToken(): Observable<boolean> {

    if(this.jwtHelper.isTokenExpired(this.getRefreshToken()))
     return of(false)

    let body=`grant_type=refresh_token&refresh_token=${this.getRefreshToken()}`

    return this.http

    .post<any>(this.url , body, {
        headers: this.headers
      }).pipe(map(response=>{

        this.storeToken(response.access_token);
        this.storeRefreshToken(response.refresh_token)

        return true;
      }));

  }
  private storeRefreshToken(token:string){
    localStorage.setItem('refresh_token',token)
  }
  private getRefreshToken():string{
    return localStorage.getItem('refresh_token')!
  }

  isAccessTokenInvalid() {
    const token = localStorage.getItem('token');
    return !token || this.jwtHelper.isTokenExpired(token);
  }

  private  storeToken(token: string) {
    localStorage.setItem('token', token);
  }
  private  getToken():string{
   return localStorage.getItem('token')!
  }

  clearTokens() {
    localStorage.removeItem('token');
    localStorage.removeItem('refresh_token');
  }

  logout(){
    this.clearTokens()

    return of(true)
  }
}
