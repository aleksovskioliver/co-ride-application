import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  submitted = false
  errorMessage = ''
  form = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthService,
    private router: Router) { }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true
    this.errorMessage = ''

    if (this.form.valid) {
      this.auth.loginUser(this.form.value).subscribe({
        next: () => {          
          this.router.navigateByUrl("/").then(() => {window.location.reload()})
        },
        error: error => {
          this.errorMessage = error
        }
      })
    }
  }

}
