import { Injectable } from '@angular/core';
import { BaseService } from '../base.service';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../models/User';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

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
    return this.httpClient.post<User>(`${environment.baseUrl}/rest/login`, this.json, this.getDefaultHttpOptions());
  }

  register(user: User): Observable<User> {
    this.json = JSON.stringify(user);
    return this.httpClient.post<User>(`${environment.baseUrl}/rest/login/register`, this.json, this.getDefaultHttpOptions());
  }
}
