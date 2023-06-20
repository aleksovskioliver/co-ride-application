import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDTO } from '../../../models/UserDTO';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  id: number | undefined;
  user: UserDTO | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.userService.getUserById(this.id!)
    .subscribe({
      next: (data)=> this.user = data
    })
  }

  updateUser(){
    this.userService.updateUser(this.id!,this.user!)
    .subscribe(() => {
      this.router.navigate(['/profile']);
    })
  }

  onSubmit(){
    this.updateUser()
  }

}
