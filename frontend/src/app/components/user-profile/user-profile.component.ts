import { Component, OnInit } from '@angular/core';
import {UserServices} from '../../services/user/user.service';
import {User} from '../../../models/User';
import {Router} from '@angular/router'

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  private contentLoaded: boolean = false;
  protected user: User;

  constructor(private userService: UserServices, private router: Router) { }

  ngOnInit() {
    // TODO get session id or something
    // this.userService.getAccountById(1).subscribe(data => {
    //  this.user = data.user;
    //  this.contentLoaded = true;
    // });
  }

  isContentLoaded(): boolean
  {
    return this.contentLoaded;
  }
}
