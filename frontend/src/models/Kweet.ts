import {User} from "./User";

export class Kweet{
  private content: string;
  private date: Date;
  private postAccount: User;
  private mentions: User[];

  constructor(content: string, date: Date, postAccount: User, mentions: User[]) {
    this.content = content;
    this.date = date;
    this.postAccount = postAccount;
    this.mentions = mentions;
  }
}
