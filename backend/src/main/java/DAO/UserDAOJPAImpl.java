package DAO;

import logic.user.Role;
import logic.user.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDAOJPAImpl implements IUserDAO
{
  @PersistenceContext
  private EntityManager em;

  @Override
  public void update(User user)
  {
    em.merge(user);
  }

  @Override
  public void create(User user)
  {
    em.persist(user);
  }

  @Override
  public User findById(int userId)
  {
    return null;
  }

  @Override
  public User search(String username)
  {
    return null;
  }

  @Override
  public List<User> getFollowing(int userId)
  {
    return null;
  }

  @Override
  public boolean addRole(Role role, int userId)
  {
    return false;
  }
}
