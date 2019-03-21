import {Role} from "./Role";

export class User
{
  private _username: string;
  private _password: string;
  private _location: string;
  private _website: string;
  private _bio: string;
  private _role: Role;

  constructor(username: string, password: string, location: string, website: string, bio: string)
  {
    this._username = username;
    this._password = password;
    this._location = location;
    this._website = website;
    this._bio = bio;
  }


  get username(): string
  {
    return this._username;
  }

  get location(): string
  {
    return this._location;
  }

  get website(): string
  {
    return this._website;
  }

  get bio(): string
  {
    return this._bio;
  }

  get role(): Role
  {
    return this._role;
  }


  set username(value: string)
  {
    this._username = value;
  }

  set location(value: string)
  {
    this._location = value;
  }

  set website(value: string)
  {
    this._website = value;
  }

  set bio(value: string)
  {
    this._bio = value;
  }

  set role(value: Role)
  {
    this._role = value;
  }
}

