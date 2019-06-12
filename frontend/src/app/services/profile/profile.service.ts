import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BaseService} from '../base.service';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService extends BaseService {

  private wsUri = 'ws://localhost:8080/socket/';

  constructor(private httpClient: HttpClient) {
    super();
  }

  followUser(visitorId: number, visitedId: number) {
    return this.httpClient.post(
      `${environment.baseUrl}/profilepage/${visitedId}/${visitorId}`,
      this.getDefaultHttpOptions());
  }
}
