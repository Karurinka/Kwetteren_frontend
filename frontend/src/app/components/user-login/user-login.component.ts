import { Component, OnInit } from '@angular/core';
import {UserServices} from '../../services/user/user.service';
import {User} from '../../../models/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {
  username = '';
  password = '';
  user: User;

  constructor(private userService: UserServices, private router: Router) { }

  ngOnInit() {

  }

  loginSubmit() {

    this.user = new User();
    this.user.username = this.username;
    this.user.password = this.password;

    this.userService.login(this.user).subscribe(
      data => {
        if (data != null) {
          this.user = data;
          console.log(data);
        }
      });
  }
}
