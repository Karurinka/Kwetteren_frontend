import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/User';
import { UserServices } from '../../services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent implements OnInit {

  username = '';
  password = '';
  error = ' ';
  user: User;
  private loggingIn: boolean = false;

  constructor(private userService: UserServices, private router: Router) {
  }

  ngOnInit() {

  }

  registerSubmit() {
    this.loggingIn = true;

    this.user = new User();
    this.user.username = this.username;
    this.user.password = this.password;

    this.userService.register(this.user).subscribe(
      data => {
        if (data != null) {
          this.user = data;
          this.router.navigate(['/home']);
        }
      }, error => {
        this.error = error;
        this.loggingIn = false;
      });
  }

}
