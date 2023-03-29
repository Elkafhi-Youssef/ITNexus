import {Component, OnInit,HostListener} from '@angular/core';
import {AccountService} from "../../services/account/account.service";
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth-service.service";


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit{
  isPopupVisible = false;
  isMenuVisible  = false;
  isScrolled = false;
  xihaja(){
    console.log("hi");
  }
  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 0;
  }
  showPopup() {
    if (this.isPopupVisible == false){
      this.isPopupVisible = true;
    }else{
      this.isPopupVisible = false;
    }
  }
  showMenu() {
    if (this.isMenuVisible == false){
      this.isMenuVisible = true;
    }else{
      this.isMenuVisible = false;
    }
  }

  public loggedIn: boolean = false;
  public currentUser: any = null;
  constructor(
    private authService:AuthService,
    private router: Router,
    private accountService : AccountService,
    private localStorageService : LocalStorageService,
  ) {
  }

  ngOnInit(): void {
    this.accountService.authStatus.subscribe(res=>{
      this.loggedIn = res
      this.currentUser = this.localStorageService.getInfos();

  })
  }
  logout(){
    this.localStorageService.remove("token")
    this.accountService.changeStatus(false);
    this.router.navigateByUrl("/")
  }
  test(){
    this.authService.test()
  }

}
