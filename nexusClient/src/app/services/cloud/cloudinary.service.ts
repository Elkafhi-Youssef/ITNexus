import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CloudinaryService {

  constructor(private http:HttpClient,) { }
uploadImage(imageFile: any){
  return this.http.post<any>('https://api.cloudinary.com/v1_1/def5gccjn/image/upload',imageFile)
}
}
