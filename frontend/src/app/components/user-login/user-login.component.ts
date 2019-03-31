import { Component, OnInit } from '@angular/core';
import {UserServices} from "../../services/user/user.service";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {
  private test: number;
  constructor(private userService: UserServices) { }

  ngOnInit() {
  }

}
