import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AuthService} from "../../services/auth.service";
import {ReservationService} from "../../services/reservation.service";
import {ReservationComponent} from "../reservation/reservation.component";
import {Router} from "@angular/router";
import {PaymentFormDialogComponent} from "../payment-form-dialog/payment-form-dialog.component";

@Component({
  selector: 'app-dialog',
  templateUrl: 'dialog.component.html',
  styleUrls: ['dialog.component.scss']
})
export class DialogComponent {
  constructor(public _dialogRef: MatDialogRef<DialogComponent>,
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
    // Open the payment form dialog directly
    this.openPaymentFormDialog();
  }

  openPaymentFormDialog() {
    const dialogRef = this._dialog.open(PaymentFormDialogComponent, {
      width: '400px',
      data: {reservationId: this.data.reservation.id}
    });

    dialogRef.afterClosed().subscribe({
      next: () => this.reserved(),
      error: err => console.log("error message", err)
    })
  }
}
