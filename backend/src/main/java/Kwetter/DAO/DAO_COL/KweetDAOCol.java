package Kwetter.DAO.DAO_COL;

import Kwetter.DAO.IKweetDAO;
import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.utility.HibernateSessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Default
public class KweetDAOCol implements IKweetDAO
{
  CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<>();
  UserDAOCol userService;

  int Id = 0;

  @Override
  public HibernateSessionFactory getHibernateSessionFactory()
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
  public void delete(Kweet tweet)
  {
    kweets.remove(tweet);
  }

  @Override
  public void update(Kweet entity)
  {
    for (int i = 0; i < kweets.size(); i++)
    {
      if (kweets.get(i).getId() == entity.getId())
      {
        kweets.set(i, entity);
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
    for (Kweet k : this.kweets)
    {
      if (k.getId() == (int) id)
      {
        return k;
      }
    }
    return null;
  }

  @Override
  public List<Kweet> getPersonalKweets(int userId)
  {
    User user = userService.findById(userId);
    List<Kweet> kweets = new ArrayList<>();
    for (Kweet k : this.kweets)
    {
      if (k.getPostAccount().getId() == userId || user.getFollowing().contains(k.getPostAccount()))
      {
        kweets.add(k);
      }
    }
    return kweets;
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
    List<Kweet> kweets = new ArrayList<>();
    for (Kweet k : this.kweets)
    {
      if (k.getContent().contains(content))
      {
        kweets.add(k);
      }
    }
    return kweets;
  }

  public void setTweets(CopyOnWriteArrayList<Kweet> tweets)
  {
    this.kweets = kweets;
  }

  public void setAccountService(UserDAOCol userService)
  {
    this.userService = userService;
  }
}
