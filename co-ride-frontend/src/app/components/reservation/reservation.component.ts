import {Component, Input, OnInit} from '@angular/core';
import {MapService} from 'src/app/services/map.service';
import {Reservation} from 'src/app/models/Reservation';
import {ReservationService} from 'src/app/services/reservation.service';
import {AuthService} from 'src/app/services/auth.service';
import {Router} from '@angular/router';
import * as moment from 'moment';
import {MatDialog} from "@angular/material/dialog";
import {PaymentMethodDialogComponent} from "../payment-method-dialog/payment-method-dialog.component";
import {ReservationResponse} from "../../models/ReservationResponse";
import {User} from "../../models/User";
import {RatingService} from "../../services/rating.service";


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  @Input() admin = false;
  @Input() customer = false;
  @Input() reservations: Reservation[] = []
  @Input() reservationsR: ReservationResponse[] = []
  p: number = 1;
  errorMessage = '';
  success = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private service: ReservationService,
    private mapService: MapService,
    private ratingService: RatingService,
    private dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  reserved(r: Reservation) {
    this.errorMessage = '';
    this.success = false;

    const dialogRef = this.dialog.open(PaymentMethodDialogComponent, {
      width: '400px',
      data: {reservation: r}
    });
  }

  unreserved(r: Reservation) {
    this.errorMessage = '';

    if (this.authService.isLoggedIn()) {

      this.service.removeCustomerFromReservation(r.id).subscribe({
        next: () => {
          r.availableSeats++
          window.location.reload()
        },
        error: error => {
          this.errorMessage = error
        }
      })
    } else {
      this.router.navigateByUrl("/login")
    }
  }

  setMarker(r: Reservation) {
    this.mapService.addMarker(r.pickupLocation.lat, r.pickupLocation.lng);
    this.mapService.addMarker(r.dropoutLocation.lat, r.dropoutLocation.lng);
  }

  formatTime(time: string) {
    const date = moment(time)
    return date.format("LLL")
  }

  isExpired(r: Reservation): boolean {
    return moment(r.endTime).isBefore(moment())
  }

  onRateChange(driver: User, event: any): void {
    this.ratingService.rateUser(driver.id, event).subscribe();
  }
}
