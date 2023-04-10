import {Component, OnInit} from '@angular/core';
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {PersonsService} from "../../services/person/persons.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id!: string | null;
  events: any[]= [];
  profile:any | null = null;
  skills =['Angular', 'HTML', 'CSS', 'JavaScript', 'Spring Boot'];

  constructor(private storage: LocalStorageService, private person: PersonsService) {
    // this.events = [
    //   { offerTitle: 'dev full stack', date: '15/10/2020 10:30', icon: 'pi pi-shopping-cart', color: '#9C27B0', image: 'game-controller.jpg' },
    //   // { status: 'Processing', date: '15/10/2020 14:00', icon: 'pi pi-cog', color: '#673AB7' },
    //   // { status: 'Shipped', date: '15/10/2020 16:15', icon: 'pi pi-shopping-cart', color: '#FF9800' },
    //   // { status: 'Delivered', date: '16/10/2020 10:00', icon: 'pi pi-check', color: '#607D8B' }
    // ];
  }

  ngOnInit(): void {
    this.storage.get("id")
    console.log(this.storage.get("id"))
    this.id = this.storage.get("id")
    this.person.profile(this.id).subscribe(
      res => {
        console.log("data",res)
        this.profile = res;
        this.events= res.workofferListOfRH
      },
      error => {
        console.log("error")
      })


  }
  truncateDescription(description: string): string {
    const maxLength = 150;
    if (description.length > maxLength) {
      return description.substring(0, maxLength) + '...';
    }
    return description;
  }


}
