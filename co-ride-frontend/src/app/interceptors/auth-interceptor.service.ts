import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = localStorage.getItem("id_token");

    if (idToken) {
      const cloned = req.clone({
        setHeaders: {
          Authorization: `Bearer ${idToken}`
        }
      });
      return next.handle(cloned)
    }
    else {
      return next.handle(req)
    }
  }
}
