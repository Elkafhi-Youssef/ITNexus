import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {NgxUiLoaderService} from "ngx-ui-loader";
import { PaginatorModule } from 'primeng/paginator';
import {LocalStorageService} from "../../services/storage/local-storage.service";
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
  first: number = 0; rows: number = 2;
  private idPerson!: string;
  constructor(private router: Router,private offersService:OffersServiceService,private loaderService: NgxUiLoaderService,private token:LocalStorageService) { }

  ngOnInit(): void {
    // this.loaderService.start();
        this.offersService.getAllOffers(0,2).subscribe(res=>{
          console.log(res.data[0].totalElements)
          this.offerss = res.data[0].content;
          if (res.data[0].totalElements % 2 ==0){
            this.totalElements = res.data[0].totalElements
          }else{
            this.totalElements = res.data[0].totalElements+1
          }
          console.log("hna", this.totalElements)

          // this.loaderService.stop();
        })
    }
// { first: number; rows: number; }
  onPageChange(event:any ) {
    if (event.first >0 ){
      this.first = event.first-1;
    }else{
      this.first = event.first;
    }

    this.rows = event.rows;

    this.offersService.getAllOffers(this.first,2).subscribe(res=>{
      console.log(res.data[0].totalPages)
      this.offerss = res.data[0].content;
      this.totalElements = res.data[0].totalElements
      // this.loaderService.stop();
    })
    console.log( this.first)
    console.log(this.rows)
  }
  tokken(){
    let token =this.token.getInfos().scope;
    console.log(token)

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

  offers:any[] = [
    {
      id:1,
      image: 'https://randomuser.me/api/portraits/women/10.jpg',
      firstName: 'Jane',
      lastName: 'Doe',
      company: 'Acme Inc.',
      date: new Date(),
      title: 'FullStack Developer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      skills: ['Angular', 'HTML', 'CSS', 'JavaScript', 'Spring Boot']
    },
    {
      id:2,
      image: 'https://randomuser.me/api/portraits/men/10.jpg',
      firstName: 'John',
      lastName: 'Doe',
      company: 'XYZ Inc.',
      date: new Date(),
      title: 'Backend Developer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      skills: ['Java', 'Spring Boot', 'SQL']
    },{
      id:3,
      image: 'https://randomuser.me/api/portraits/men/10.jpg',
      firstName: 'John',
      lastName: 'Doe',
      company: 'XYZ Inc.',
      date: new Date(),
      title: 'Backend Developer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      skills: ['Java', 'Spring Boot', 'SQL']
    },{id:4,
      image: 'https://randomuser.me/api/portraits/men/10.jpg',
      firstName: 'John',
      lastName: 'Doe',
      company: 'XYZ Inc.',
      date: new Date(),
      title: 'Backend Developer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      skills: ['Java', 'Spring Boot', 'SQL']
    },
    {id:5,
      image: 'https://randomuser.me/api/portraits/men/10.jpg',
      firstName: 'John',
      lastName: 'Doe',
      company: 'XYZ Inc.',
      date: new Date(),
      title: 'Backend Developer',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      skills: ['Java', 'Spring Boot', 'SQL']
    }
  ];

}
