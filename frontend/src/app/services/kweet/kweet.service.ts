import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

//@Path("/kweets")
@Injectable()
export class KweetService
{

  constructor(private httpClient: HttpClient)
  {
  }

  //@POST
  create(content: string, id: number){

  }

  //@GET
  //@Path("/{id}")
  getById(id: number){

  }

  //@GET
  //@Path("/all")
  getAllTweets(){

  }

  //@GET
  getPersonalTweets(id: number){

  }

  //@GET
  //@Path("/search")
  search(content: string){

  }

  //@GET
  //@Path("/remove/{Id}")
  delete(id: number){

  }

  //@POST
  //@Path("/edit")S
  edit(id: number, content: string){

  }
}
