import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {LocalStorageService} from "../storage/local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(this.localStorageService.isLogin());
  private role = new BehaviorSubject<string>(this.localStorageService.getInfos())
  authStatus = this.isLoggedInSubject.asObservable();
  roleUserConnected = this.role.asObservable();
  constructor( private localStorageService: LocalStorageService,) { }
  changeStatus(value: boolean){
    this.isLoggedInSubject.next(value);
  }

}
