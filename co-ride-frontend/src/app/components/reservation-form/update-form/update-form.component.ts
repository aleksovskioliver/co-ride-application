import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Reservation } from 'src/app/models/Reservation';
import { ReservationDTO } from 'src/app/models/ReservationDTO';
import { ReservationUpdateDTO } from 'src/app/models/ReservationUpdateDTO';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-update-form',
  templateUrl: './update-form.component.html',
  styleUrls: ['./update-form.component.css']
})
export class UpdateFormComponent implements OnInit {

  id: number | undefined;
  reservation: ReservationUpdateDTO | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private reservationService: ReservationService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.reservationService.getReservationById(this.id!)
    .subscribe({
      next: (data) => this.reservation = data
    })
  }

  updateReservation(){
    this.reservationService.updateReservation(this.id!,this.reservation!)
    .subscribe(()=>{
      this.router.navigate(['/profile']);
    })
  }

  onSubmit(){
    this.updateReservation();
  }

}
