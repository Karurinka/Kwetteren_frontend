package backend.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Admin extends User
{
  //constructor
  public Admin(String username, String password, String location, String website, String biography)
  {
    super(username, password, location, website, biography);
  }

  //methods
  public User GetUsers()
  {
      throw new NotImplementedException();
  }
}
