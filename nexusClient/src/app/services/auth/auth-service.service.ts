import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthResponse} from "../../models/auth-response";
import {env} from "../../../../config/env";
import {Test} from "../../models/test";
const url = env.url;
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http:HttpClient,
    

  ) { }
  login(email:string,password : string){
    const headers = new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    );
    return this.http.post<AuthResponse>(`${url}/api/token`,{email, password},{headers});
  }
  test(){

    return this.http.get<Test>(`${url}/api/comments/test`).subscribe(res=>{
      console.log(res)
    });
  }
}
