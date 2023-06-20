import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  isLoggedIn: boolean = false
  loggedInUser: User | null = null

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: data => {
        if(data.user) {
          this.loggedInUser = data.user
          this.isLoggedIn = true
        }
        else {
          this.isLoggedIn = false
        }
      }
    })
  }

  logout() {
    this.authService.logout()
    this.isLoggedIn = false
    this.router.navigateByUrl("/")
  }

}
