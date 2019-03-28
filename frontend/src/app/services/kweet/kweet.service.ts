import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {BaseService} from '../base.service';
import {Observable} from 'rxjs';


//@Path("/kweets")
@Injectable({providedIn: 'root'}
)
export class KweetService extends BaseService
{

  constructor(private httpClient: HttpClient)
  {
    super();
  }


  //@POST
  // TODO: kweet JSON
  create()
  {
    return this.httpClient.post(
      `${environment.baseUrl}/kweets`, this.getDefaultHttpOptions());
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
