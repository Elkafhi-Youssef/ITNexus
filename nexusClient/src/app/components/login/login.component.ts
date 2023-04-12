import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth-service.service";
import {Router} from "@angular/router";
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {AccountService} from "../../services/account/account.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!:FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private localStorageService: LocalStorageService,
    private router: Router,
    private accountService : AccountService,
    private toast:NgToastService
  ) {
    this.loginForm = this.fb.group({
      email: ["youssef@gmail.com",[Validators.required,Validators.email]],
      password: ["youssef1999",[Validators.required]]
    });
  }
  login(){
    const values = this.loginForm.value;

    if(values.email && values.password){
      this.authService.login(values.email,values.password).subscribe(
        (res) => {
          this.accountService.changeStatus(true)
            this.localStorageService.set("token",res.token);
            this.localStorageService.set("id",res.id);
            this.router.navigateByUrl('/home');
          this.toast.success({detail:'Success',summary:'Login successful', sticky:true,position:'tr',duration:3000})
            console.log(res)
          },error => {
          this.router.navigateByUrl('');
          this.toast.error({detail:'Error',summary:"email or password incorrect", sticky:true,position:'tr',duration:3000})

        }
      )
    }

  }


}
