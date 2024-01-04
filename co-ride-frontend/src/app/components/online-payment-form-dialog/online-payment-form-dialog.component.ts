import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {StripeService} from 'ngx-stripe';
import {PaymentService} from "../../services/payment.service";

@Component({
  selector: 'app-payment-form',
  templateUrl: './online-payment-form-dialog.component.html',
  styleUrls: ['./online-payment-form-dialog.component.css']
})
export class OnlinePaymentFormDialogComponent implements OnInit {
  stripeTest!: FormGroup;
  elements: any;
  card: any;
  loading = false;
  error: string | undefined;

  constructor(private fb: FormBuilder,
              private stripeService: StripeService,
              private paymentService: PaymentService,
              private dialogRef: MatDialogRef<OnlinePaymentFormDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.stripeTest = this.fb.group({
      name: ['', Validators.required]
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
    this.stripeService
      .createToken(this.card, {name})
      .subscribe(result => {
        if (result.token) {
          this.paymentService.processPayment(result.token.id, name, this.data.reservationId).subscribe()
          this.dialogRef.close(result.token);
        } else if (result.error) {
          this.error = result.error.message;
        }
        this.loading = false;
      });
  }
}
