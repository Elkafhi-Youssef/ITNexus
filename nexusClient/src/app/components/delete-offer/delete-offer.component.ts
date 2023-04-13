import {Component, Input} from '@angular/core';
import {OffersServiceService} from "../../services/offres/offer-service.service";

@Component({
  selector: 'app-delete-offer',
  templateUrl: './delete-offer.component.html',
  styleUrls: ['./delete-offer.component.css']
})
export class DeleteOfferComponent{
  @Input() modelDeleteOffer:boolean = false;
  @Input() offerSelected:any ;
  @Input() toggleModelDeleteOffer= ():void=>{}

  constructor(private offerService:OffersServiceService ){
  }

  delete(idOffer: number) {
    console.log(idOffer)
    this.offerService.deleteOffer(idOffer).subscribe(
      (res) => {
        console.log(res)
      }, (error) => {
        console.log(error)
      }
    )

  }




}
