package Kwetter.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import Kwetter.endpoints.controllers.KweetController;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.net.URI;
import java.util.List;

import org.glassfish.jersey.linking.InjectLink;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "kweetdao.getPersonalKweets", query = "select t from  Kweet k, User u  where u.Id = :id " + "and (k.postAccount.Id in (select f.Id from u.following b) or k.postUser.Id = :id ) " + "order by k.Date desc"),
    @NamedQuery(name = "kweetdao.getPostedKweets", query = "select k from  Kweet k where k.postUser.Id = :id order by k.Date desc"),
    @NamedQuery(name = "tweetdao.search", query = "select k from  Kweet k where k.content like :content order by k.Date desc")})

public class Kweet extends KweetModel
{

  @InjectLink(resource = KweetController.class)
  URI link;

  @Column(length = 140)
  private String content;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date date;

  @ManyToOne
  @JsonBackReference(value = "tweets")
  private User postAccount;

  @OneToMany
  @JsonBackReference
  private List<User> hearted;

  @ManyToMany(mappedBy = "mentions")
  @JsonBackReference
  private List<User> mentions;

  //constructor
  public Kweet() {}

  public Kweet(String content, User postAccount)
  {
    this.content = content;
    this.postAccount = postAccount;
  }

  //getters
  public String getContent()
  {
    return content;
  }

  public User getPostAccount()
  {
    return postAccount;
  }

  public Date getDate()
  {
    Date date = getDate();
    return date;
  }

  //setters
  public void setContent(String content)
  {
    this.content = content;
  }

  public void setPostAccount(User postAccount)
  {
    this.postAccount = postAccount;
  }

  public void setDate(Date date) { this.date = date;}

  public List<User> getHearted()
  {
    if (hearted == null)
    {
      return new ArrayList<>();
    }
    return hearted;
  }

  public void setHearted(List<User> hearted)
  {
    this.hearted = hearted;
  }

  public boolean addHearted(User user)
  {
    if (hearted == null)
    {
      hearted = new ArrayList<>();
    }
    if (this.hearted.contains(user))
    {
      return false;
    }
    this.hearted.add(user);
    return true;
  }

  public boolean removeHearted(User user)
  {
    if (hearted == null)
    {
      hearted = new ArrayList<>();
    }
    try
    {
      this.hearted.remove(user);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public List<User> getMentions()
  {
    return mentions;
  }

  public boolean addMention(User user)
  {
    if (mentions == null) {mentions = new ArrayList<>();}
    return mentions.add(user);
  }

  public boolean removeMention(User user)
  {
    return mentions.remove(user);
  }

  public void setMentions(List<User> mentions)
  {
    this.mentions = mentions;
  }

  public URI getLink()
  {
    return link;
  }

  public void setLink(URI link)
  {
    this.link = link;
  }

}
