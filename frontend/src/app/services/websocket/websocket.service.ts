import { Injectable } from '@angular/core';
import { BaseService } from '../base.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService extends BaseService {
  userId: number;
  public websocket;
  connected: boolean;

  constructor(private httpClient: HttpClient) {
    super();
  }

  connect(userId: number): void {
    this.userId = userId;
    console.log(`This is the url ${environment.baseUrl}/socket/${userId}`);
    this.websocket = new WebSocket(`${environment.baseUrl}/socket/${userId}`);
    this.websocket.onopen = evt => {
      console.log('connected');
    };

    this.connected = true;
  }

  disconnect(): void {
    this.connected = false;
    this.websocket.onclose = evt => {
      console.log('disconnected');
    };
    this.websocket.close();
  }
}
