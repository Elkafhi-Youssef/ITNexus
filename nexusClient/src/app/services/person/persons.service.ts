import { Injectable } from '@angular/core';
import {env} from "../../../../config/env";
import {HttpClient} from "@angular/common/http";
import * as http from "http";

const url =  env.url;
@Injectable({
  providedIn: 'root'
})
export class PersonsService {

  constructor(private http:HttpClient) {  }

  profile(id: string|null){
    return this.http.get<any>(`${url}/api/persons/${id}`)
  }
}
