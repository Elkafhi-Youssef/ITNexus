import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {LocalStorageService} from "../storage/local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(this.localStorageService.isLogin());
  authStatus = this.isLoggedInSubject.asObservable();
  constructor( private localStorageService: LocalStorageService,) { }
  changeStatus(value: boolean){
    this.isLoggedInSubject.next(value);
  }

}
