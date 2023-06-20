import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Reservation } from '../models/Reservation';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { MyLocation } from '../models/MyLocation';
import { ReservationDTO } from '../models/ReservationDTO';
import { ReservationUpdateDTO } from '../models/ReservationUpdateDTO';


@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  url = "http://localhost:8080/api"

  constructor(private http: HttpClient) { }

  getReservations(pickupCity: string, dropoutCity: string): Observable<Reservation[]> {
    if ((pickupCity == '') && (dropoutCity != '')) {
      return this.http.get<Reservation[]>(`${this.url}/reservation?dropoutCity=${dropoutCity}`)
    } else if ((pickupCity != '') && (dropoutCity == '')) {
      return this.http.get<Reservation[]>(`${this.url}/reservation?pickupCity=${pickupCity}`);
    } else if ((pickupCity == '') && (dropoutCity == '')) {
      return this.http.get<Reservation[]>(`${this.url}/reservation`).pipe(tap(data => console.log(data)));
    } else {
      return this.http.get<Reservation[]>(`${this.url}/reservation?pickupCity=${pickupCity}&dropoutCity=${dropoutCity}`);
    }
  }

  getDriverCreatedReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.url}/reservation/driver`)
  }

  createReservation(reservation: ReservationDTO) {
    this.http.post(`${this.url}/reservation/create`, reservation).subscribe({
      next: data => console.log(data)
    })
  }

  addCustomerToReservation(id: number) {
    return this.http.post(`${this.url}/reservation/addCustomer/${id}`, id).pipe(
      tap(it => console.log(it)),
      catchError(error => throwError(() => new Error(error.error)))
    )
  }

  removeCustomerFromReservation(id: number) {
    return this.http.post(`${this.url}/reservation/removeCustomer/${id}`, id).pipe(
      tap(it => console.log(it)),
      catchError(error => throwError(() => new Error(error.error)))
    )
  }

  getReservationById(id: number): Observable<Reservation>{
    return this.http.get<Reservation>(`http://localhost:8080/api/reservation/find/${id}`);
  }

  getLocations(): Observable<MyLocation[]> {
    return this.http.get<MyLocation[]>(`${this.url}/location/getLocations`)
  }

  deleteReservation(id: number) {
    return this.http.delete(`${this.url}/reservation/delete/${id}`).pipe(
      tap(it => console.log(it)),
      catchError(error => throwError(() => new Error(error.error))
      )
    )
  }

  updateReservation(id: number, reservation: ReservationUpdateDTO){
    return this.http.put(`http://localhost:8080/api/reservation/update/${id}`, reservation);
  }
}
