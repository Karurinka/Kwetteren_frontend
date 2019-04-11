import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { BaseService } from '../base.service';
import {Observable} from 'rxjs';
import {Kweet} from '../../../models/Kweet';

@Injectable({providedIn: 'root'}
)
export class KweetService extends BaseService {

  protected json: string;

  constructor(private httpClient: HttpClient) {
    super();
  }

  createKweet(userId: number, content: string) {
    return this.httpClient.put(
      `${environment.baseUrl}/rest/kweet/${userId}`, content, this.getDefaultHttpOptions());
  }

  getTimeline(userId: number): Observable<Kweet[]> {
    return this.httpClient.get<Kweet[]>(`${environment.baseUrl}/rest/kweet/timeline/${userId}`, this.getDefaultHttpOptions());
  }

  searchKweet(searchContent: string): Observable<Kweet[]> {
    this.json = JSON.stringify(searchContent);
    return this.httpClient.post<Kweet[]>(`${environment.baseUrl}/rest/kweet/search`, this.json, this.getDefaultHttpOptions());
  }
}
