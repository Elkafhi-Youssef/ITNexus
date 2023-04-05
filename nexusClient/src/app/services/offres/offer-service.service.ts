import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from 'src/app/models/auth-response';
import {env} from "../../../../config/env";
import {AppResponse} from "../../models/appResponse";
const url = env.url;
@Injectable({
  providedIn: 'root'
})
export class OffersServiceService {

  constructor(private http:HttpClient) { }
  getAllOffers(){
    return this.http.get<AppResponse>(`${url}/api/workoffers`);
  }
}
