import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {OfferByIdService} from "../../services/offerById/offer-by-id.service";

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent {
  offerId!: string | null ;
  offer:any;

constructor(private route: ActivatedRoute,private offerByIdService: OfferByIdService) { }

ngOnInit(): void {
  // @ts-ignore
  this.offerId = this.route.snapshot.paramMap.get('id')?.toString();
  console.log(this.offerId);
  // @ts-ignore
  this.offerByIdService.getOfferById(this.offerId).subscribe(res=>{
    console.log(res);
    this.offer= res;
  })

}

}
