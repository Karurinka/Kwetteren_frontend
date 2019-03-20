import {User} from "./User";

export class Kweet{
  private content: string;
  private date: Date;
  private postAccount: User;
  private mentions: User[];
}
