import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from 'src/app/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  createReservation = false

  constructor(
    private authService: AuthService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  onClick() {
    if (this.authService.isLoggedIn()) {
      this.createReservation = !this.createReservation
    } else {
      this.router.navigateByUrl("/login")
    }
  }

}
