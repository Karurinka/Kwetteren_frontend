package backend.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public abstract class User
{
  //variables
  private String username;
  private String password;
  private String location;
  private String website;
  private String biography;
  private List<User> following;
  private List<User> followers;
  private List<Kweet> kweets;
  private List<Kweet> heartedKweets;

  //constructor
  public User(String username, String password, String location, String website, String biography)
  {
      this.username = username;
      this.password = password;
      this.location = location;
      this.website = website;
      this.biography = biography;
  }

  //getters
  public String getUsername()
  {
      return username;
  }

  public String getPassword()
  {
      return password;
  }

  public String getLocation()
  {
      return location;
  }

  public String getWebsite()
  {
      return website;
  }

  public String getBiography()
  {
      return biography;
  }

  //setters

  public void setUsername(String username)
  {
      this.username = username;
  }

  public void setPassword(String password)
  {
      this.password = password;
  }

  public void setLocation(String location)
  {
      this.location = location;
  }

  public void setWebsite(String website)
  {
      this.website = website;
  }

  public void setBiography(String biography)
  {
      this.biography = biography;
  }

  //methods
  public Boolean ChangeUsername()
  {
      throw new NotImplementedException();
  }

  public Boolean ChangeBiography()
  {
      throw new NotImplementedException();
  }

  public Kweet GetLatest10Kweets()
  {
      throw new NotImplementedException();
  }

  public Boolean FollowUser(User follower, User following)
  {
      throw new NotImplementedException();
  }
}
