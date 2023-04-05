import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {NgxUiLoaderService} from "ngx-ui-loader";
import { PaginatorModule } from 'primeng/paginator';
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
  skills =['Angular', 'HTML', 'CSS', 'JavaScript', 'Spring Boot'];
offerss!:any[];
  first: number = 0; rows: number = 10;
  constructor(private router: Router,private offersService:OffersServiceService,private loaderService: NgxUiLoaderService) { }

  ngOnInit(): void {
    // this.loaderService.start();
        this.offersService.getAllOffers(0,2).subscribe(res=>{
          console.log(res.data[0].content)
          this.offerss = res.data[0].content;
          // this.loaderService.stop();
        })
    }
// { first: number; rows: number; }
  onPageChange(event:any ) {
    this.first = event.first/10;
    this.rows = event.rows;

    this.offersService.getAllOffers(this.first,2).subscribe(res=>{
      console.log(res.data[0].content)
      this.offerss = res.data[0].content;
      // this.loaderService.stop();
    })
    console.log( this.first)
    console.log(this.rows)
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
