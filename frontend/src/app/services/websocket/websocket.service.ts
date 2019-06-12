import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  username: string;
  public websocket;
  connected: boolean;

  private wsUri = 'ws://localhost:8080/socket/';

  constructor() {

  }

  connect(username: string): void {
    this.username = username;
    console.log(`This is the url ${this.wsUri + this.username}`);    this.websocket = new WebSocket(this.wsUri + this.username);
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
