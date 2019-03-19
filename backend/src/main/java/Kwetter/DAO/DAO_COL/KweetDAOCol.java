package Kwetter.DAO.DAO_COL;

import Kwetter.DAO.IKweetDAO;
import Kwetter.Models.Kweet;
import Kwetter.Models.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class KweetDAOCol implements IKweetDAO
{
  CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<>();
  UserDAOCol accountService;

  int Id = 0;

  @Override
  public EntityManager getEntityManager()
  {
    return null;
  }

  @Override
  public void create(Kweet kweet)
  {
    kweet.setId(Id);
    kweets.add(kweet);
    Id++;
  }

  @Override
  public void delete(Kweet kweet)
  {
    kweets.remove(kweet);
  }

  @Override
  public void update(Kweet kweet)
  {
    for (int i = 0; i < kweets.size(); i++)
    {
      if (kweets.get(i).getId() == kweet.getId())
      {
        kweets.set(i, kweet);
        break;
      }
    }
  }

  @Override
  public ArrayList<Kweet> getAll()
  {
    return new ArrayList(kweets);
  }

  @Override
  public Kweet findById(int id)
  {
    for (Kweet t : this.kweets)
    {
      if (t.getId() == (int) id)
      {
        return t;
      }
    }
    return null;
  }

  @Override
  public List<Kweet> getPersonalKweets(int userId)
  {
    User user = accountService.findById(userId);
    List<Kweet> tweets = new ArrayList<>();
    for (Kweet k : this.kweets)
    {
      if (k.getPostAccount().getId() == userId || user.getFollowing().contains(k.getPostAccount()))
      {
        tweets.add(k);
      }
    }
    return tweets;
  }

  @Override
  public List<Kweet> getPostedKweets(int id)
  {
    List<Kweet> kweets = new ArrayList<>();
    for (Kweet k : this.kweets)
    {
      if (k.getPostAccount().getId() == id)
      {
        kweets.add(k);
      }
    }
    return kweets;
  }

  @Override
  public List<Kweet> search(String content)
  {
    List<Kweet> tweets = new ArrayList<>();
    for (Kweet k : this.kweets)
    {
      if (k.getContent().contains(content))
      {
        tweets.add(k);
      }
    }
    return tweets;
  }

  public void setTweets(CopyOnWriteArrayList<Kweet> tweets)
  {
    this.kweets = tweets;
  }

  public void setAccountService(UserDAOCol accountService)
  {
    this.accountService = accountService;
  }
}
