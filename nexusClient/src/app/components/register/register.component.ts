import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from "../../services/auth/auth-service.service";
import {LocalStorageService} from "../../services/storage/local-storage.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private register: AuthService ) { }

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
    if (this.registerForm.valid) {
      const formData = this.registerForm.value;
      this.register.register(formData).subscribe(
        response => {
          console.log('User registered successfully');
        },
        error => {
          console.error('Error registering user:', error);
        }
      );
    }
  }

}
