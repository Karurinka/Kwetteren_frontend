package logic.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "tweetdao.getPersonalTweets",
    query = "select t from  Kweet k, User u  where u.Id = :id " +
      "and (k.postAccount.Id in (select f.Id from u.following b) or k.postUser.Id = :id ) " +
      "order by k.Date desc"),
  @NamedQuery(name = "tweetdao.getPostedTweets", query = "select k from  Kweet k where k.postUser.Id = :id order by k.Date desc"),
  @NamedQuery(name = "tweetdao.search", query = "select k from  Kweet k where k.content like :content order by k.Date desc")})
public class Kweet extends KweetModel
{
  //variables
  private String content;
  private Integer maxValue;
  private User user;
  private Date date;

  //constructor
  public Kweet(String content, User user)
  {
    this.user = user;
    date = new Date();
    this.content = content;
    maxValue = 140;
  }

  //getters
  public String getContent()
  {
    return content;
  }

  public User getUser()
  {
    return user;
  }

  public Date getDate()
  {
    return date;
  }

  //setters
  public void setContent(String content)
  {
    this.content = content;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

}
