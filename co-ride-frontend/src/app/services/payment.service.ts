import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private baseUrl = 'http://localhost:8084/api/payment';

  constructor(private http: HttpClient) {
  }

  processPayment(token: string, name: string, reservationId: number): Observable<any> {
    const payload = {token, name, reservationId};
    return this.http.post(`${this.baseUrl}`, payload);
  }
}
