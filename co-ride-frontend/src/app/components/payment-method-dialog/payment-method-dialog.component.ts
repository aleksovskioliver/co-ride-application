import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AuthService} from "../../services/auth.service";
import {ReservationService} from "../../services/reservation.service";
import {Router} from "@angular/router";
import {OnlinePaymentFormDialogComponent} from "../online-payment-form-dialog/online-payment-form-dialog.component";

@Component({
  selector: 'app-dialog',
  templateUrl: 'payment-method-dialog.component.html',
  styleUrls: ['payment-method-dialog.component.scss']
})
export class PaymentMethodDialogComponent {
  constructor(public _dialogRef: MatDialogRef<PaymentMethodDialogComponent>,
              private _authService: AuthService,
              private _service: ReservationService,
              private _router: Router,
              private _dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  reserved() {
    if (this._authService.isLoggedIn()) {
      this._service.addCustomerToReservation(this.data.reservation.id).subscribe({
        next: () => {
          console.log('in next', this.data);
          this.data.reservation.availableSeats--
          // this.success = true;
          this._dialogRef.close();
        },
        error: error => {
          // this.errorMessage = error
        }
      })
    } else {
      this._router.navigateByUrl("/login")
      this._dialogRef.close();

    }
  }

  payInCash() {
    this.reserved()
  }

  payOnline() {
    this.openPaymentFormDialog();
  }

  openPaymentFormDialog() {
    const dialogRef = this._dialog.open(OnlinePaymentFormDialogComponent, {
      width: '400px',
      data: {reservationId: this.data.reservation.id}
    });

    dialogRef.afterClosed().subscribe({
      next: () => this.reserved(),
      error: err => console.log("error message", err)
    })
  }
}
