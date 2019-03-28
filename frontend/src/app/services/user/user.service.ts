import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from '../../../environments/environment';
import {User} from "../../../models/User";
import {BaseService} from "../base.service";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

//@Path("/users")
@Injectable({
  providedIn: 'root'
})
export class UserServices extends BaseService
{
  constructor(private httpClient: HttpClient) {
    super();
  }

  //@POST
  create(username: string, password: string, location: string, website: string, bio: string): Observable<any>
  {
    return this.httpClient.post<any>(`${environment.baseUrl}/users`,
      new User(username, password, location, website, bio), httpOptions);
  }

  //@GET
  getAllAccounts(): Observable<any>
  {
    return this.httpClient.get<any>(`${environment.baseUrl}/users`,
      httpOptions);
  }

  //@GET
  //@Path("/{userId}")
  getAccountById(userId: number): Observable<any>
  {
    return this.httpClient.post<any>(`${environment.baseUrl}/users/{userId}`,
      httpOptions);
  }

  //@GET
  //@Path("/tweets/{userId}")
  getTweetsByAccountId()
  {
    return this.httpClient.post<any>(`${environment.baseUrl}/users/username/{userName}`,
      httpOptions);
  }

  //@GET
  //@Path("/username/{userName}")
  //TODO: implement
  getAccountByUsername()
  {

  }

  //@GET
  //@Path("/search/{name}")
  search(name: string)
  {
    return this.httpClient.get<any>(`${environment.baseUrl}/users/search/{name}`,
      httpOptions);
  }

  //@GET
  //@Path("/follow/{Id}")
  followToggle(id: number)
  {
    return this.httpClient.get<any>(`${environment.baseUrl}/users/follow/{Id}` +
      httpOptions);
  }

  //@GET
  //@Path("/followers/{Id}")
  followers(id: number)
  {
    return this.httpClient.get<any>(`${environment.baseUrl}/users/followers/{Id}` +
      httpOptions);
  }

  //@GET
  //@Path("/role/add/{role}/{Id}")
  //TODO: create role model for adding and removing
  RoleAdd(role: string, id: number)
  {

  }

  //@GET
  //@Path("/role/remove/{role}/{Id}")
  RoleRemove(role: string, id: number)
  {

  }

  //@POST
  //@Path("/edit/password")
  editPassword(currentPass: string, newPass: string, id: number): Observable<any>
  {
    return this.httpClient.post<any>(`${environment.baseUrl}/users/edit/password`,
      httpOptions);
  }

  //@POST
  //@Path("/edit")
  //TODO: create edit model for user
  edit(id: number, username: string, bio: string, location: string, website: string)
  {

  }

}
