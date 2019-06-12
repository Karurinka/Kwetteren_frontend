import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseService } from '../base.service';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../../../models/User';

@Injectable({
  providedIn: 'root'
})
export class ProfileService extends BaseService {

  private wsUri = 'ws://localhost:8080/socket/';

  constructor(private httpClient: HttpClient) {
    super();
  }

  getFollowing(userId: number): Observable<User> {
    return this.httpClient.get<User>(
      `${environment.baseUrl}/profilepage/${userId}/following`,
      this.getDefaultHttpOptions());
  }

  getFollowers(userId: number) {
    return this.httpClient.get<User>(
      `${environment.baseUrl}/profilepage/${userId}/followers`,
      this.getDefaultHttpOptions());
  }

  followUser(visitorId: number, visitedId: number) {
    return this.httpClient.post(
      `${environment.baseUrl}/profilepage/${visitedId}/${visitorId}`,
      this.getDefaultHttpOptions());
  }
}
