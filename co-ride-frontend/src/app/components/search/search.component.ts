import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { Reservation } from 'src/app/models/Reservation';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  reservations: Reservation[] = []
  loading = true
  searchField = new FormControl('');
  searchField2 = new FormControl('');

  constructor(private service: ReservationService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations(){
    this.route.queryParams.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(queryParams=>this.service.getReservations(this.searchField.value,this.searchField2.value))
    ).subscribe({
      next: (data)=>{
        this.loading = false
        this.reservations = data
      },
      error: (error)=> console.log("get reservation error", error)
    })
  }
}
