import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from "../../services/auth/auth-service.service";
import {LocalStorageService} from "../../services/storage/local-storage.service";
import {CloudinaryImage} from '@cloudinary/url-gen';
import {fill} from '@cloudinary/url-gen/actions/resize'
import {CloudinaryService} from "../../services/cloud/cloudinary.service";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm!: FormGroup;
  imageUrl!: String;
  file!:File;
  constructor(private formBuilder: FormBuilder, private register: AuthService, private uploadcdi:CloudinaryService ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: [''],
      password: ['', [Validators.required, Validators.minLength(8)]],
      image: [''],
      tel: [''],
      role: [false]
    });
  }

   onSubmit() {
     this.uploadImage().subscribe(res=>{
       console.log("here we will show ", res.secure_url  )
       this.imageUrl = res.secure_url
     },null,()=>{
       if (this.registerForm.valid) {
         const formData = this.registerForm.value;
         formData.image = this.imageUrl;
         console.log("show the content of formData",formData)
         console.log("show the content of image",this.imageUrl)
         this.register.register(formData).subscribe(
           response => {
             console.log('User registered successfully');
           },
           error => {
             console.error('Error registering user:', error);
           }
         );
       }
     })


  }
  // onFileChange(event: any) {
  //   const reader = new FileReader();
  //   reader.readAsDataURL(event.target.files[0]);
  //   console.log(event.target.files[0])
  //   reader.onload = (event) => {
  //     if (reader.result) {
  //       console.log(reader.result)
  //       this.selectedImage = reader.result.toString()
  //     }
  //   }
  // }
  onFileChange(event: any){
    this.file = event.target.files[0]
  }
  uploadImage(){
    const form = new FormData();
    form.append("file", this.file);
    form.append("upload_preset","sajoselva");
    return this.uploadcdi.uploadImage(form);
  }

}
