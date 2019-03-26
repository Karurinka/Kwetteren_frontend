package Kwetter.services;

import Kwetter.DAO.IKweetDAO;
import Kwetter.DAO.IUserDAO;
import Kwetter.Event.LogEvent;
import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.utils.JPA;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class KweetService implements Serializable
{
  @Inject
  @JPA
  IKweetDAO kweetDAO;

  @Inject
  @JPA
  IUserDAO userDAO;


  public Kweet findById(int id)
  {
    return (Kweet) kweetDAO.findById(id);
  }

  public List<Kweet> getAllTweets()
  {
    return kweetDAO.getAll();
  }

  public List<Kweet> postedTweets(int userId)
  {
    return kweetDAO.getPostedKweets(userId);
  }

  public Kweet create(String content, int userId)
  {

    User user = userDAO.findById(userId);
    Kweet kweet = new Kweet(content, user);

    kweetDAO.create(kweet);
    userDAO.update(user);

    return kweet;
  }

  public Kweet edit(int id, String content)
  {
    Kweet kweet = findById(id);
    kweet.setContent(content);
    kweetDAO.update(kweet);
    return kweet;
  }

  public List<Kweet> getPersonalTweets(int Id)
  {
    return kweetDAO.getPersonalKweets(Id);
  }

  public List<Kweet> search(String content)
  {
    return kweetDAO.search(content);
  }

  public boolean remove(int id)
  {
    Kweet kweet = (Kweet) kweetDAO.findById(id);
    try
    {
      kweetDAO.delete(kweet);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public void setTweetDAO(IKweetDAO IKweetDAO)
  {
    this.kweetDAO = IKweetDAO;
  }

  public void setAccountDAO(IUserDAO IUserDAO)
  {
    this.userDAO = IUserDAO;
  }

  public void logCreateKwetter(@Observes LogEvent event) {event.printLine("Created a tweet");}
}
