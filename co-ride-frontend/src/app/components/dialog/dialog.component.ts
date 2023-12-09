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

  closeDialog(): void {
    this._dialogRef.close();
  }

  payInCash() {
    if (this._authService.isLoggedIn()) {
      this._service.addCustomerToReservation(this.data.reservation.id).subscribe({
        next: () => {
          console.log('in next', this.data.reservation.id);
          this.data.reservation.availableSeats--
          // this.success = true;
          // window.location.reload();
          this._dialogRef.close();
        },
        error: error => {
          // this.errorMessage = error
        }
      })
    } else {
      this._router.navigateByUrl("/login")
    }
  }

  payOnline() {
    // Open the payment form dialog directly
    this.openPaymentFormDialog();
  }

  openPaymentFormDialog() {
    const dialogRef = this._dialog.open(PaymentFormDialogComponent, {
      width: '400px', // Adjust the width as needed
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('Payment form dialog closed', result);
      // Add your handling logic here if needed
    });
  }
}
