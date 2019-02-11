package main.java.DAO;

import main.java.logic.user.User;

import javax.persistence.EntityManager;

//@Stateless
public class UserDAOImpl implements UserDAO
{
  //@PersistenceContext
  private EntityManager em;

  //@Override
  public void updateUser(User user)
  {
    em.merge(user);
  }

  //@Override
  public void createUser(User user)
  {
    em.persist(user);
  }
}
