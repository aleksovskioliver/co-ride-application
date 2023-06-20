import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reservation } from 'src/app/models/Reservation';
import { User } from 'src/app/models/User';
import { ReservationService } from 'src/app/services/reservation.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User | null = null
  reservations: Reservation[] = []
  driverReservations: Reservation[] = []
  error: string = ''

  constructor(
    private userService: UserService,
    private reservationService: ReservationService,
    private router: Router) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: resp => {
        if (resp.user) {
          this.user = resp.user
        }
        if (resp.error) {
          this.error = resp.error
        }
      },
      error: error => console.log(error)
    })

    this.userService.getUserReservations().subscribe({
      next: data => {
        this.reservations = data
      }
    })

    this.reservationService.getDriverCreatedReservations().subscribe({
      next: data => {
        console.log(data)
        this.driverReservations = data
      }
    })
  }

  updateUser(id: number){
    this.router.navigate(['update',id])
  }

  updateVehicle(id: number){
    this.router.navigate(['vehicle',id])
  }

}
