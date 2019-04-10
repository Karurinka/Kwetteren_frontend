import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {BaseService} from '../base.service';

@Injectable({providedIn: 'root'}
)
export class KweetService extends BaseService {

  protected json: string;

  constructor(private httpClient: HttpClient) {
    super();
  }

  // TODO: kweet JSON as parameter
  createKweet(userId: number, content: string) {
    this.json = JSON.stringify(content);
    return this.httpClient.put(
      `${environment.baseUrl}/rest/kweet/${userId}`, this.json, this.getDefaultHttpOptions());
  }

  //@GET
  //@Path("/{id}")
  getById(id: number)
  {

  }

  //@GET
  //@Path("/all")
  getAllTweets()
  {

  }

  //@GET
  getPersonalTweets(id: number)
  {

  }

  //@GET
  //@Path("/search")
  search(content: string)
  {

  }

  //@GET
  //@Path("/remove/{Id}")
  delete(id: number)
  {

  }

  //@POST
  //@Path("/edit")S
  edit(id: number, content: string)
  {

  }
}
