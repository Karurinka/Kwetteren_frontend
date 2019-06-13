import { Component, OnInit } from '@angular/core';
import { UserServices } from '../../services/user/user.service';
import { User } from '../../../models/User';
import { KweetService } from '../../services/kweet/kweet.service';
import { Kweet } from '../../../models/Kweet';
import { ProfileService } from '../../services/profile/profile.service';
import { WebsocketService } from '../../services/websocket/websocket.service';

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
  followers: User[];
  followerUser: User;
  following: User[];
  followingUser: User;

  constructor(private userService: UserServices, private kweetService: KweetService,
              private profileService: ProfileService, private wsService: WebsocketService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
    this.visitedUser = JSON.parse(localStorage.getItem('visitedUser'));

    this.userService.getUserById(this.user.userId).subscribe(userdData => {
      this.user = userdData;
    });

    this.kweetService.getLatestKweets(this.user.userId).subscribe(data => {
      this.kweets = data;
      this.contentLoaded = true;
      return;
    });

    this.profileService.getFollowers(this.user.userId).subscribe( data => {
      this.followers = (data as unknown as User[]);
      for (const user of this.followers) {
        this.userService.getUserById(user.userId).subscribe(userData => {
          this.followerUser = userData;
        });
      }
    });

    this.profileService.getFollowing(this.user.userId).subscribe( data => {
      this.following = (data as unknown as User[]);
      for (const user of this.following) {
        this.userService.getUserById(user.userId).subscribe(userData => {
          this.followingUser = userData;
        });
      }
    });
    this.handleMessage();
    this.contentLoaded = true;
  }

  followUser() {
    this.profileService.followUser(this.user.userId, this.visitedUser.userId).subscribe( data => {

    });
  }

  handleMessage() {
    this.wsService.websocket.onmessage = evt => {
      console.log(`message received ${evt.data}`);
      const t = JSON.parse(evt.data);
      console.log(this.kweets);
      this.kweets.push(t);
      console.log(this.kweets);

    };
  }

  isContentLoaded(): boolean {
    return this.contentLoaded;
  }
}
