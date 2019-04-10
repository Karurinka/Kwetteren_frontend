import { Component, OnInit } from '@angular/core';
import { KweetService } from '../../services/kweet/kweet.service';
import { User } from '../../../models/User';
import { Kweet } from '../../../models/Kweet';

@Component({
  selector: 'app-kweet-create',
  templateUrl: './kweet-create.component.html',
  styleUrls: ['./kweet-create.component.scss']
})
export class KweetCreateComponent implements OnInit {
  user: User;
  kweetContent: string;
  kweet: Kweet;

  constructor(private kweetService: KweetService) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
    console.log('User: ' + this.user.userId);
  }

  createKweet() {
    this.kweetService.createKweet(this.user.userId, this.kweetContent).subscribe();
  }
}
