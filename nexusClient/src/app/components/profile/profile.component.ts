import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {PersonsService} from "../../services/person/persons.service";
import {Router} from "@angular/router";
import {OffersServiceService} from "../../services/offres/offer-service.service";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id!: string | null;
  modelDeleteOffer:boolean = false
  offerSelected:any
  events: any[] = [];
  offerForm!: FormGroup;

  public role = "";
  profile: any | null = null;
  visible!: boolean;

  showDialog() {
    this.visible = true;
  }

  skills = ['Angular', 'HTML', 'CSS', 'JavaScript', 'Spring Boot'];

  constructor(private storage: LocalStorageService, private person: PersonsService, private router: Router, private fb: FormBuilder, private offerService: OffersServiceService, private toast: NgToastService) {
    this.offerForm = this.fb.group({
      offerTitle: ["", [Validators.required]],
      offerDescription: ["", [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.role = this.storage.getInfos().scope;
    this.storage.get("id")
    console.log(this.storage.get("id"))
    this.id = this.storage.get("id")
    this.person.profile(this.id).subscribe(
      res => {
        console.log("data", res)
        this.profile = res;
        this.events = res.workofferListOfRH
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

  showAllApplayes(id: number) {
    this.router.navigate(['/applayesoffer/', id]);
  }

  addOffer() {
    const values = this.offerForm.value;
    if (values.offerTitle && values.offerDescription) {
      this.offerService.addOffer(values.offerTitle, values.offerDescription).subscribe({
        next: (res) => {
          console.log("added successful")
          this.toast.error({
            detail: 'Error',
            summary: "check that you have a company",
            sticky: true,
            position: 'tr',
            duration: 3000
          });
          console.log(res)
        },
        error: error => {
          console.log("added successful")
          this.storage.get("id")
          console.log(this.storage.get("id"))
          this.id = this.storage.get("id")
          this.person.profile(this.id).subscribe(
            res => {
              console.log("data", res)
              this.profile = res;
              this.events = res.workofferListOfRH
            },
            error => {
              console.log("error")
            })
          this.offerForm.reset();
          this.toast.success({
            detail: 'Success',
            summary: 'the offer created successfully',
            sticky: true,
            position: 'tr',
            duration: 3000
          })
        }
      });


    }

  }


  toggleModelDeleteOffer(){
    this.modelDeleteOffer =!this.modelDeleteOffer;
  }


}

