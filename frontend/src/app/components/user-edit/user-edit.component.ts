import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/User';
import { UserServices } from '../../services/user/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
  user: User;
  visitedUser: User;
  contentLoaded = false;
  savingData = false;

  constructor(private userService: UserServices) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
    this.visitedUser = JSON.parse(localStorage.getItem('visitedUser'));

    console.log(this.user);
    this.userService.getUserById(this.user.userId).subscribe( data => {
      this.user = data;
      console.log(this.user);
    });
    this.contentLoaded = true;
  }

  editUser() {
    this.savingData = true;
    this.userService.editProfile(this.user.userId, this.visitedUser.userId, this.user).subscribe(userData =>{
      this.user = userData;
      this.savingData = false;
    });
  }
}
