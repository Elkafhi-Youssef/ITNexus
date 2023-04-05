import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppResponse} from "../../models/appResponse";
import {env} from "../../../../config/env";
const url = env.url;
@Injectable({
  providedIn: 'root'
})
export class OfferByIdService {

  constructor(private http:HttpClient) { }

  getOfferById(id: string){
    return this.http.get<any>(`${url}/api/workoffers/${id}`);
  }
}
