import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import {StripeService} from 'ngx-stripe';
import {PaymentService} from "../../services/payment.service";

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form-dialog.component.html',
  styleUrls: ['./payment-form-dialog.component.css']
})
export class PaymentFormDialogComponent implements OnInit {
  stripeTest!: FormGroup;
  elements: any;
  card: any;
  loading = false;
  error: string | undefined;

  constructor(private fb: FormBuilder, private stripeService: StripeService, private paymentService: PaymentService, private dialogRef: MatDialogRef<PaymentFormDialogComponent>) {
  }

  ngOnInit() {
    this.stripeTest = this.fb.group({
      name: ['', Validators.required],
      email: ['', Validators.required]
    });

    this.stripeService.elements().subscribe(elements => {
      this.elements = elements;
      this.card = elements.create('card');
      this.card.mount('#card-element');
    });
  }

  onSubmit() {
    this.loading = true;
    const name = this.stripeTest.get('name')!!.value;
    const email = this.stripeTest.get('email')!!.value;
    this.stripeService
      .createToken(this.card, {name})
      .subscribe(result => {
        if (result.token) {
          console.log(result.token);
          //result.token.id
          this.paymentService.processPayment(result.token.id, name, email).subscribe()
          this.dialogRef.close(result.token);

          // Now you can pass result.token to your backend
        } else if (result.error) {
          this.error = result.error.message;
        }
        this.loading = false;
      });
  }
}
