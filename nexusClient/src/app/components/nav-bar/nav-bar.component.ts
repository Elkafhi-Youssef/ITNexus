import {Component, OnInit,HostListener} from '@angular/core';
import {AccountService} from "../../services/account/account.service";
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth-service.service";
import {PersonsService} from "../../services/person/persons.service";


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit{
  isPopupVisible = false;
  isMenuVisible  = false;
  isScrolled = false;
  public loggedIn: boolean = false;
  public role ="";
  infoUser:any|null
  public currentUser: any = null;
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


  constructor(
    private authService:AuthService,
    private router: Router,
    private accountService : AccountService,
    private localStorageService : LocalStorageService,
    private userRole:LocalStorageService,
    private  personService:PersonsService
  ) {
  }

  ngOnInit(): void {
    this.accountService.authStatus.subscribe(res=>{
      this.loggedIn = res
      this.currentUser = this.localStorageService.getInfos();

  })
    this.role =this.userRole.getInfos().scope;
    // this.accountService.roleUserConnected.subscribe(res=>{
    //   console.log("getting the role",res);
    //   this.role = res;
    // })
    this.isMenuVisible = false;
    this.personService.getInfoRersonLogin().subscribe({
      next: (res)=>{
        this.infoUser = res
        console.log(res,"info user")
      }
    })
  }
  logout(){
    this.localStorageService.remove("token")
    this.accountService.changeStatus(false);
    this.router.navigateByUrl("/")
    this.isPopupVisible = false;
  }
  profile(){

  }
  test(){
    this.authService.test()
  }

}
