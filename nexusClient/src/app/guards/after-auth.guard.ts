import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {LocalStorageService} from "../services/storage/local-storage.service";
import {AccountService} from "../services/account/account.service";

@Injectable({
  providedIn: 'root'
})
export class AfterAuthGuard implements CanActivate {
  constructor(private router: Router,private localStorage : LocalStorageService, private accountService : AccountService ) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean{
    if (this.localStorage.isLogin()){
      this.router.navigateByUrl("/home")
      this.accountService.changeStatus(true)

      return false;
    }
    return true;
  }

}
