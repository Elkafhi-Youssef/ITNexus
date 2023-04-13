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
  getAllOffers(page: number, size: number){
    return this.http.get<AppResponse>(`${url}/api/workoffers?page=${page}&size=${size}`);
  }
  getOfferById(id: string){
    return this.http.get<any>(`${url}/api/workoffers/${id}`);
  }
  getApplayersOfOffers(id: string){
    return this.http.get<any>(`${url}/api/workoffers/applayers/${id}`);
  }
  applyOffer(idOffer:number){
    const headers = new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    );
    return this.http.post<any>(`${url}/api/workoffers/applyOffer`,{"idoffer":idOffer},{headers});
  }
  addOffer(offerTitle:string,offerDescription:string){
    const headers = new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    );
    return this.http.post<string>(`${url}/api/workoffers`,{"offerTitle":offerTitle,"offerDescription":offerDescription},{headers});
  }
  deleteOffer(idOffer:number){
    const headers = new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    );
    return this.http.delete(`${url}/api/workoffers/${idOffer}`,{headers})
  }
}
