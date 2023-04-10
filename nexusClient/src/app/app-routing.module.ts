import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthGuard} from "./guards/auth.guard";
import {AfterAuthGuard} from "./guards/after-auth.guard";
import { OfferDetailsComponent } from './components/offer-details/offer-details.component';
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";

const routes: Routes = [
  {path:"",redirectTo:"/login",pathMatch:'full'},
  {path:"login", component:LoginComponent, canActivate: [AfterAuthGuard] },
  {path:"home",component:HomeComponent ,  },
  {path:"offerdetails/:id",component:OfferDetailsComponent},
  {path:"register",component:RegisterComponent,  canActivate: [AfterAuthGuard]},
  {path:"profile",component:ProfileComponent,  canActivate: [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
