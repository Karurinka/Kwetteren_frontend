import { Component, OnInit } from '@angular/core';
import { KweetService } from '../../services/kweet/kweet.service';
import { User } from '../../../models/User';
import { Kweet } from '../../../models/Kweet';
import { Router } from '@angular/router';

@Component({
  selector: 'app-kweet-create',
  templateUrl: './kweet-create.component.html',
  styleUrls: ['./kweet-create.component.scss']
})
export class KweetCreateComponent implements OnInit {
  user: User;
  kweetContent: string;
  kweet: Kweet;

  constructor(private kweetService: KweetService, private router: Router) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('loggedUser'));
  }

  createKweet() {
    this.kweetService.createKweet(this.user.userId, this.kweetContent).subscribe( data => {
      this.router.navigate(['/home']);
    });
  }
}
