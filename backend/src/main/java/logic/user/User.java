package logic.user;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "userdao.search", query = "select u from  User u where u.username like :usernbame order by u.username desc"),
    @NamedQuery(name = "userdao.getFollowing", query = "SELECT u FROM User u where :id in (select f.Id from u.following f)")
})
public class User extends KweetModel
{
  //variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String username;
  private String password;
  private String location;
  private String website;
  private String biography;
  private Role role;
  @OneToMany
  private List<User> following;
  @OneToMany
  private List<User> followers;
  @OneToMany
  private List<Kweet> kweets;


  //general user constructor
  public User(String username, String password, String location, String website, String biography, Role role)
  {
      this.username = username;
      this.password = password;
      this.location = location;
      this.website = website;
      this.biography = biography;
      this.role = role;
  }

  //user constructor with recursion and all kweets
  public User(String username, String password, String location, String website, String biography, Role role,
  List<User> following, List<User> followers, List<Kweet> kweets)
  {
    this.username = username;
    this.password = password;
    this.location = location;
    this.website = website;
    this.biography = biography;
    this.role = role;
    this.following = following;
    this.followers = followers;
    this.kweets = kweets;
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

  @Override
  public String toString()
  {
    return "User{" + "username='" + username + '\'' + ", location='" + location +
      '\'' + ", website='" + website + '\'' + ", biography='" + biography + '\'' +
      ", role=" + role + ", following=" + following +
      ", followers=" + followers + ", kweets=" + kweets + '}';
  }
}
