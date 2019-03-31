import { Component, OnInit } from '@angular/core';
import {UserServices} from "../../services/user/user.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserServices) { }

  ngOnInit() {
  }


  // TODO load user get by id/username
}
