import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/User';
import {Kweet} from '../../../models/Kweet';
import {KweetService} from '../../services/kweet/kweet.service';
import {UserServices} from '../../services/user/user.service';

@Component({
  selector: 'app-kweet-homepage',
  templateUrl: './kweet-homepage.component.html',
  styleUrls: ['./kweet-homepage.component.scss']
})
export class KweetHomepageComponent implements OnInit {
  user: User;
  visitedUser: User;
  kweets: Kweet[];
  private contentLoaded: boolean = false;

  constructor(private kweetService: KweetService, private userService: UserServices) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
    this.visitedUser = JSON.parse(localStorage.getItem('visitedUser'));
    this.kweetService.getTimeline(this.user.userId).subscribe(data => {
      this.kweets = data;
      for (const kweet of this.kweets) {
        kweet.date = new Date(kweet.date);
      }
      this.contentLoaded = true;
      return;
    });
  }

  isContentLoaded() {
    return this.contentLoaded;
  }
}
