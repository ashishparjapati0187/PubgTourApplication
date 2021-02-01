import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
////import { map ,retry} from 'rxjs/operators';
//import { Observable } from 'rxjs';

import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME:string='jwt-decode';  //npm install --save jwt-decode

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
springEndPoint:string;
token:string;

  constructor(private httpClient:HttpClient){
    this.springEndPoint="http://localhost:8087/user";
  }
  registerUser(newUser){
  console.log("registered user="+newUser);
  const registerUrl=this.springEndPoint+"/register";
  return this.httpClient.post(registerUrl,newUser,{responseType:"text"})
 
}

loginUser(newUser):any{
  const loginUrl=this.springEndPoint+"/login";
  return this.httpClient.post(loginUrl,newUser);   //automaticaly detect json data
  
}
setToken(token:string){
  return localStorage.setItem(TOKEN_NAME,token);   // setting token to TOKEN_NAME after login
}
getToken(){
  return localStorage.getItem(TOKEN_NAME);
}

deleteToken(){
  return localStorage.removeItem(TOKEN_NAME);
}

getTokenExpirationDate(token:string):Date{
  const decoded=jwt_decode(token);
  if(decoded.exp == undefined) return null;
  const date=new Date(0);
  date.setUTCSeconds(decoded.exp);
  return date;
}

isTokenExpired(token?:string):boolean{
  if(!token) token=this.getToken();
  if(!token) return true;
  const date=this.getTokenExpirationDate(token);
  if(date === undefined || date ===null) return false;
  return !(date.valueOf() > new Date().valueOf());
}
}
