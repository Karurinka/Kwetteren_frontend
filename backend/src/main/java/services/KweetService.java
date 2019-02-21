package services;

import DAO.IKweetDAO;
import DAO.IUserDAO;
import com.sun.jersey.spi.inject.Inject;
import logic.user.Kweet;
import logic.user.User;
import utility.JPA;

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
    return kweetDAO.findById(id);
  }

  public List<Kweet> getAllTweets()
  {
    return kweetDAO.getAllKweets();
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
    Kweet tweet = kweetDAO.findById(id);
    try
    {
      kweetDAO.delete(tweet);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public boolean hearth(int tweetId, int userId)
  {
    boolean success = false;
    try
    {
      Kweet kweet = kweetDAO.findById(tweetId);
      User user = userDAO.findById(userId);
      if (kweet == null || user == null) throw new Exception("Tweet or account not found");

      kweetDAO.update(kweet);
      return success;
    } catch (Exception e)
    {
      return false;
    }
  }

  public void setTweetDAO(IKweetDAO IKweetDAO)
  {
    this.kweetDAO = IKweetDAO;
  }

  public void setAccountDAO(IUserDAO IUserDAO)
  {
    this.userDAO = IUserDAO;
  }

  //TODO: fix logging issues
  //public void logCreateKwetter(@Observes LogEvent event) {event.printLine("Created a tweet");}
}
