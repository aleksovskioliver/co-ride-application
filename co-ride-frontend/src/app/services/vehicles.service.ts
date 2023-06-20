import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Vehicle } from '../models/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehiclesService {

  url = 'http://localhost:8080/api/vehicle'

  constructor(private http: HttpClient) { }

  addVehicle(vehicle: Vehicle) {
    this.http.post(`${this.url}/create`, vehicle).pipe(
      catchError(error => throwError(() => new Error(error.error)))
    )
  }

  getVehicleById(id: number): Observable<Vehicle>{
    return this.http.get<Vehicle>(`${this.url}/${id}`)
  }

  updateVehicle(id: number, vehicle: Vehicle){
    return this.http.put(`${this.url}/${id}`,vehicle)
  }
}
