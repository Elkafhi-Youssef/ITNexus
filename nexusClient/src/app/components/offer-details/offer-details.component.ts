import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {OfferByIdService} from "../../services/offerById/offer-by-id.service";
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {NgToastService} from "ng-angular-popup";
import {LocalStorageService} from "../../services/storage/local-storage.service";

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent {
  offerId!: string | null ;
  offer:any|null;

constructor(private route: ActivatedRoute,private offerService: OffersServiceService,private offersService:OffersServiceService,private toast:NgToastService,private token:LocalStorageService) { }

ngOnInit(): void {
  // @ts-ignore
  this.offerId = this.route.snapshot.paramMap.get('id')?.toString();
  console.log(this.offerId);
  // @ts-ignore
  this.offerService.getOfferById(this.offerId).subscribe(res=>{
    console.log(res);
    this.offer= res;
  })

}
  apply(idOffer:number){
    // @ts-ignore
    this.idPerson = this.token.get("id")
    this.offersService.applyOffer(idOffer).subscribe(
      (res:any)=>{
        this.toast.error({detail:'Error',summary:"error in applying", sticky:true,position:'tr',duration:3000})
      },(error:any) => {
        this.toast.success({detail:'Success',summary:'Applied successfully', sticky:true,position:'tr',duration:3000})
      })
  }

}
