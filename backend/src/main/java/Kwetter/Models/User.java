package Kwetter.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@NamedQueries(value = {@NamedQuery(name = "userdao.search", query = "select u from  User u where u.username like :usernbame order by u.username desc"), @NamedQuery(name = "userdao.findByUserName", query = "SELECT u FROM User u where a.username = :username"), @NamedQuery(name = "userdao.getFollowing", query = "SELECT u FROM User u where :id in (select f.Id from u.following f)")})
public class User extends KweetModel
{
  //variables
  @Column(unique = true, nullable = false)
  private String username;
  @Column(unique = true, nullable = false)
  private String password;
  private String location;
  private String website;
  private String biography;
  private Role role;

  @ManyToMany(mappedBy = "accounts")
  @JsonManagedReference
  private List<Role> groups;

  @OneToMany(mappedBy = "postAccount")
  private List<Kweet> kweets;

  @OneToMany
  @JsonManagedReference
  private List<User> following;

  //general models
  public User() {}

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

  @XmlTransient
  public List<Kweet> getTweets()
  {
    return kweets;
  }

  public void setTweets(List<Kweet> tweets)
  {
    this.kweets = tweets;
  }

  public List<User> getFollowing()
  {
    if (following == null)
    {
      return new ArrayList<User>();
    }
    return following;
  }

  public void setFollowing(List<User> following)
  {
    this.following = following;
  }

  public void addFollowing(User tofollow)
  {
    if (following == null)
    {
      following = new ArrayList<>();
    }
    following.add(tofollow);
  }

  public void removeFollowing(User toUnfollow)
  {
    if (following == null)
    {
      following = new ArrayList<>();
    }
    following.remove(toUnfollow);
  }

  public List<Role> getGroups()
  {
    return groups;
  }

  public void setGroups(List<Role> groups)
  {
    this.groups = groups;
  }

  public void addGroup(Role role)
  {
    if (!groups.contains(role))
    {
      groups.add(role);
    }
  }

  public void removeGroup(Role role)
  {
    if (groups.contains(role))
    {
      groups.remove(role);


    }
  }
}

