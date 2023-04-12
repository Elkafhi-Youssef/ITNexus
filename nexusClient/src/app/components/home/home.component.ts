import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {NgxUiLoaderService} from "ngx-ui-loader";
import { PaginatorModule } from 'primeng/paginator';
import {LocalStorageService} from "../../services/storage/local-storage.service";
import { NgToastService } from 'ng-angular-popup' ;
interface Offer {
id:number;
  image: string;
  firstName: string;
  lastName: string;
  company: string;
  date: Date;
  title: string;
  description: string;
  skills: string[];
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit{
  totalElements!:number;
  skills =['Angular', 'HTML', 'CSS', 'JavaScript', 'Spring Boot'];
offerss:any[] | null= null;
  first: number = 0; rows: number = 6;
  private idPerson!: string;
  constructor(private router: Router,private offersService:OffersServiceService,private loaderService: NgxUiLoaderService,private token:LocalStorageService,private toast:NgToastService) { }

  ngOnInit(): void {
    // this.loaderService.start();
        this.offersService.getAllOffers(0,6).subscribe(res=>{
          console.log("totalElements",res.data[0].totalElements)
          this.offerss = res.data[0].content;
          console.log("data offers", res.data[0].content)
          if (res.data[0].totalElements< 6){
            this.totalElements = 6
          }else if (res.data[0].totalElements % 6 == 0){
            this.totalElements = res.data[0].totalElements
          }else{
            this.totalElements = res.data[0].totalElements+1
          }
          console.log("totalElements", this.totalElements)

          // this.loaderService.stop();
        })
    }
// { first: number; rows: number; }
  onPageChange(event:any ) {
    console.log( " first waht has",event.first)
    if (event.first > 0 ){
      this.first = (event.first)/6;
    }else {
      this.first = event.first;
    }
    console.log( " first waht has mni 5rjat",event.first)

    this.rows = event.rows;

    this.offersService.getAllOffers(this.first,6).subscribe(res=>{
      console.log(res.data[0].totalPages)
      this.offerss = res.data[0].content;
      this.totalElements = res.data[0].totalElements
      console.log("total element", this.totalElements)
      // this.loaderService.stop();
    })
    console.log( " first waht has",this.first)
    console.log("rows",this.rows)
  }
goToOfferDetail(offer:any) {
  this.router.navigate(['/offerdetails', offer.workofferId]);
}

  truncateDescription(description: string): string {
    const maxLength = 150;
    if (description.length > maxLength) {
      return description.substring(0, maxLength) + '...';
    }
    return description;
  }
  apply(idOffer:number){
    // @ts-ignore
    this.idPerson = this.token.get("id")

    this.offersService.applyOffer(idOffer).subscribe(
      (res:any)=>{
      console.log(res)
    },(error:any) => {
      console.error('Error applying offer:', error);
    })
  }



}
