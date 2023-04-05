import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import {AppInterceptor} from "./interceptors/app-interceptor.interceptor";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { FooterComponent } from './components/footer/footer.component';
import { OfferDetailsComponent } from './components/offer-details/offer-details.component';
import { NgxUiLoaderModule,
  NgxUiLoaderConfig,
  NgxUiLoaderHttpModule,
  SPINNER,
  POSITION,
  PB_DIRECTION } from 'ngx-ui-loader';
import { PaginatorModule } from 'primeng/paginator';
const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  fgsColor: '#1876d1',
  fgsPosition: POSITION.centerCenter,
  fgsSize: 40,
  fgsType: SPINNER.chasingDots,
  pbDirection: PB_DIRECTION.leftToRight,
  pbThickness: 5
}



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavBarComponent,
    FooterComponent,
    OfferDetailsComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule,
      HttpClientModule,
      BrowserAnimationsModule,
      ButtonModule,
      PaginatorModule,
      PasswordModule,
      NgxUiLoaderModule,
      NgxUiLoaderModule.forRoot(ngxUiLoaderConfig),
      NgxUiLoaderHttpModule.forRoot({ showForeground: true })
    ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AppInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
