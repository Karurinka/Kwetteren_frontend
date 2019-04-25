import { Component, OnInit } from '@angular/core';
import { UserServices } from '../../services/user/user.service';
import { User } from '../../../models/User';
import { Router } from '@angular/router';
import { KweetService } from '../../services/kweet/kweet.service';
import {Kweet} from '../../../models/Kweet';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: User;
  visitedUser: User;
  kweets: Kweet[];
  private contentLoaded = false;

  constructor(private userService: UserServices, private kweetService: KweetService, private router: Router) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
    this.visitedUser = JSON.parse(localStorage.getItem('visitedUser'));

    this.kweetService.getLatestKweets(this.user.userId).subscribe(data => {
      this.kweets = data;

      this.contentLoaded = true;
      return;
    });
  }

  isContentLoaded(): boolean {
    return this.contentLoaded;
  }
}
