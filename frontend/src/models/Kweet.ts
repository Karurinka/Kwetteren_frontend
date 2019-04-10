import {User} from './User';
import {Observable} from 'rxjs';

export class Kweet{

  kweetId: number;
  userId: number;
  user: Observable<User>;
  content: string;
  date: Date;
}
