package Kwetter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.net.URI;
import java.util.List;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "kweetdao.getPersonalKweets", query = "select k from  Kweet k, User u  where u.Id = :id " + "and (k.postAccount.Id in (select b.Id from u.following b) or k.postAccount.Id = :id ) " + "order by k.date desc"),
    @NamedQuery(name = "kweetdao.getPostedKweets", query = "select k from  Kweet k where k.postAccount.Id = :id order by k.date desc"),
    @NamedQuery(name = "kweetdao.search", query = "select k from  Kweet k where k.content like :content order by k.date desc")})

public class Kweet extends KweetModel implements Serializable
{

  @Column(length = 140)
  private String content;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date date;

  @ManyToOne
  private User postAccount;

  @OneToMany
  private List<User> hearted;

  @ManyToMany(mappedBy = "mentions")
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
}
