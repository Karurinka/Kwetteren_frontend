package Kwetter.DAO.JPAImpl;

import Kwetter.DAO.DAOFacade;
import Kwetter.DAO.IUserDAO;
import Kwetter.Models.Role;
import Kwetter.Models.User;
import Kwetter.utility.HibernateSessionFactory;
import Kwetter.utility.JPA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Named
@ApplicationScoped
@JPA
public class UserDAOJPAImpl extends DAOFacade<User> implements IUserDAO
{

  @Inject
  private HibernateSessionFactory mySessionFactory;

  public UserDAOJPAImpl()
  {
    super(User.class);
  }

  @Override
  public User findByUserName(String username)
  {
    User user;
    try
    {
      Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("userdao.findByUserName");
      q.setParameter("username", username);
      user = (User) q.getSingleResult();
    } catch (Exception e)
    {
      return null;
    }
    return user;

  }

  @Override
  public List<User> search(String name)
  {
    name = name.replace(" ", "%");
    name = "%" + name + "%";
    Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("userdao.search");
    q.setParameter("name", name);
    List<User> userList = q.getResultList();
    return userList;
  }

  @Override
  public List<User> getFollowing(int Id)
  {
    Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("userdao.getFollowing");
    q.setParameter("id", Id);
    List<User> userList = q.getResultList();
    return userList;
  }

  @Override
  public boolean addRole(Role role, int id)
  {
    try
    {
      User user = findById(id);
      user.addGroup(role);
      getHibernateSessionFactory().getCurrentSession().persist(user);
    } catch (Exception e)
    {
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public boolean removeRole(Role role, int id)
  {
    try
    {
      User user = findById(id);
      user.removeGroup(role);
      getHibernateSessionFactory().getCurrentSession().persist(user);
    } catch (Exception e)
    {
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public HibernateSessionFactory getHibernateSessionFactory()
  {
    return mySessionFactory;
  }


  public void setHibernateSessionFactory(HibernateSessionFactory em)
  {
    this.mySessionFactory = mySessionFactory;
  }
}
