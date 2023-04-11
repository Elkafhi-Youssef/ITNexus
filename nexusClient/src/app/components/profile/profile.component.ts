import {Component, OnInit} from '@angular/core';
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {PersonsService} from "../../services/person/persons.service";
import {Router} from "@angular/router";

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

  constructor(private storage: LocalStorageService, private person: PersonsService, private router :Router) {
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

  showAllApplayes(id: number){
    this.router.navigate(['/applayesoffer/', id]);
  }


}
