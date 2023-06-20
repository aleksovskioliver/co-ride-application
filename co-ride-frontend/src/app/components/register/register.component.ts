import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/models/UserDTO';
import { CustomValidators } from 'src/app/providers/custom-validators';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  errorMessage = ''
  submitted = false;
  form = this.formBuilder.group({
    firstname: ['', Validators.required],
    lastname: ['', Validators.required],
    phonenumber: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(40)
      ]
    ],
    confirmPassword: ['', Validators.required],
    role: ['rider', [Validators.required]],
    model: ['', [CustomValidators.vehicleRequiredValidator]],
    make: ['', [CustomValidators.vehicleRequiredValidator]],
    seats: ['', [CustomValidators.vehicleRequiredValidator]]
  }, {
    validator: CustomValidators.mustMatch('password', 'confirmPassword')
  });

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form.get('role')!.valueChanges
      .subscribe(value => {
        if (value == 'rider') {
          this.form.get('model')?.setValue('')
          this.form.get('make')?.setValue('')
          this.form.get('seats')?.setValue('')
        }
        this.form.get('model')?.updateValueAndValidity();
        this.form.get('make')?.updateValueAndValidity();
        this.form.get('seats')?.updateValueAndValidity();
      });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.errorMessage = ''
    this.submitted = true

    if (this.form.invalid) {
      return;
    }

    const data = this.form.value
    const user: UserDTO = {
      firstName: data.firstname,
      lastName: data.lastname,
      phoneNumber: data.phonenumber,
      email: data.email,
      password: data.password,
      role: data.role,
      vehicle: data.role == 'driver' ? {
        id: 0,
        model: data.model,
        make: data.make,
        seats: data.seats
      } : null
    }
    console.log(user)

    this.auth.registerUser(user).subscribe({
      next: () => {
        this.router.navigateByUrl('/login')
      },
      error: error => {
        this.errorMessage = error.error
        console.log("Error", error)
      }
    })
  }
}
