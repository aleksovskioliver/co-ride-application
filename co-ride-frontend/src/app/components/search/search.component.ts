import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs';
import {ReservationService} from 'src/app/services/reservation.service';
import {ReservationResponse} from "../../models/ReservationResponse";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  reservations: ReservationResponse[] = []
  loading = true
  searchField = new FormControl('');
  searchField2 = new FormControl('');

  constructor(private service: ReservationService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations() {
    this.route.queryParams.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(queryParams => this.service.getReservations(this.searchField.value as string, this.searchField2.value as string))
    ).subscribe({
      next: (data) => {
        this.loading = false
        this.reservations = data
      },
      error: (error) => console.log("get reservation error", error)
    })
  }
}
