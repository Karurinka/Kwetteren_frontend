import { Injectable } from '@angular/core';
import { BaseService } from '../base.service';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { User } from '../../../models/User';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserServices extends BaseService {
  constructor(private httpClient: HttpClient) {
    super();
  }

  json: string;

  login(user: User): Observable<User> {
    this.json = JSON.stringify(user);
    return this.httpClient.post<User>(`${environment.baseUrl}/login`, this.json, httpOptions);
  }

  register(user: User): Observable<User> {
    this.json = JSON.stringify(user);
    return this.httpClient.post<User>(`${environment.baseUrl}/login/register`, this.json, this.getDefaultHttpOptions());
  }

  followUser(followedId: number, followerId: number): Observable<string> {
    return this.httpClient.post<string>(`${environment.baseUrl}/profilepage/${followedId}/${followerId}`, this.json, this.getDefaultHttpOptions());
  }

  getUserById(userId: number): Observable<User> {
    return this.httpClient.get<User>(`${environment.baseUrl}/profilepage/${userId}`, this.getDefaultHttpOptions());
  }

  editProfile(userId: number, otherId: number, user: User): Observable<User> {
    this.json = JSON.stringify(user);
    return this.httpClient.post<User>(`${environment.baseUrl}/profilepage/${userId}/edit/${userId}`, this.json, this.getDefaultHttpOptions());
  }


}
