import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {OfferByIdService} from "../../services/offerById/offer-by-id.service";
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {LocalStorageService} from "../../services/storage/local-storage.service";

@Component({
  selector: 'app-applayers-pesons',
  templateUrl: './applayers-pesons.component.html',
  styleUrls: ['./applayers-pesons.component.css']
})
export class ApplayersPesonsComponent  implements  OnInit{
  offerId!:string | null;
  applayers:any[] | null= null;
  offer:any;
  idPerson!: string | null;
  constructor(private storage: LocalStorageService,private route: ActivatedRoute ,private offerService: OffersServiceService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.offerId = this.route.snapshot.paramMap.get('idoffer')?.toString();
    console.log(this.offerId ,"id of offer")
    // @ts-ignore
    this.offerService.getApplayersOfOffers(this.offerId).subscribe(res=>{
      console.log("responce",res);
      this.applayers=res;
    })
  }


}
