package main.java.DAO;

import main.java.logic.user.Kweet;

import javax.persistence.EntityManager;

//@Stateless
public class KweetDAOImpl implements KweetDAO
{
  //@PersistenceContext
  private EntityManager em;

  //@Override
  public void deleteKweet(Kweet kweet)
  {
    em.remove(kweet);
  }
}
