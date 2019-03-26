package Kwetter.DAO.JPAImpl;

import Kwetter.DAO.DAOFacade;
import Kwetter.DAO.IUserDAO;
import Kwetter.Models.Role;
import Kwetter.Models.User;
import Kwetter.utils.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;
import java.util.List;

@Stateless
@JPA
public class UserDAOJPAImpl extends DAOFacade<User> implements IUserDAO {

  @PersistenceContext(unitName = "KwetterPU")
  EntityManager em;

  public UserDAOJPAImpl() {
    super(User.class);
  }

  @Override
  public User findByUserName(String username) {
    User user;
    try {
      Query q = em.createNamedQuery("userdao.findByUserName");
      q.setParameter("username", username);
      user = (User) q.getSingleResult();
    } catch (Exception e) {
      return null;
    }
    return user;

  }

  @Override
  public List<User> search(String name) {
    name = name.replace(" ", "%");
    name = "%" + name + "%";
    Query q = em.createNamedQuery("userdao.search");
    q.setParameter("name", name);
    List<User> userList = q.getResultList();
    return userList;
  }

  @Override
  public List<User> getFollowing(int Id) {
    Query q = em.createNamedQuery("userdao.getFollowing");
    q.setParameter("id", Id);
    List<User> userList = q.getResultList();
    return userList;
  }

  @Override
  public boolean addRole(Role role, int id) {
    try {
      User user = findById(id);
      user.addGroup(role);
      em.persist(user);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public boolean removeRole(Role role, int id) {
    try {
      User user = findById(id);
      user.removeGroup(role);
      em.persist(user);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public EntityManager getEntityManager() {
    return em;
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }
}
