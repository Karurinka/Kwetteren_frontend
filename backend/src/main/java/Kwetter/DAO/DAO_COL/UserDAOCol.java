package DAO.DAO_COL;

import DAO.DAOFacade;
import DAO.IUserDAO;
import Models.Role;
import Models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class UserDAOCol extends DAOFacade<User> implements IUserDAO
{
  CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
  int Id = 0;
  @Override
  public EntityManager getEntityManager() {
    return null;
  }

  @Override
  public void create(User account) {
    account.setId(Id);
    users.add(account);
    Id++;

  }

  @Override
  public void delete(User user) {
    users.remove(user);
  }

  @Override
  public ArrayList<User> getAll() {
    return new ArrayList(users);
  }

  @Override
  public User findById(int id) {
    for (User a : users) {
      if (a.getId() == (int) id) {
        return a;
      }
    }
    return null;
  }

  public UserDAOCol() {
    super(User.class);
  }

  @Override
  public User findByUserName(String name) {
    for (User user : users) {
      if (user.getUsername() == name) {
        return user;
      }
    }
    return null;
  }

  @Override
  public List<User> search(String name) {
    ArrayList usr = new ArrayList();
    for (User user : users) {
      if ((user.getUsername()).contains(name)) {
        usr.add(user);
      }
    }
    return usr;
  }

  @Override
  public boolean addRole(Role role, int Id) {
    try {
      User user = findById(Id);
      user.addGroup(role);
    }catch (Exception e){
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public boolean removeRole(Role role, int Id) {
    try {
      User user = findById(Id);
      user.removeGroup(role);
    }catch (Exception e){
      System.out.println(e);
      return false;
    }
    return true;
  }

  @Override
  public List<User> getFollowing(int Id) {
    User user = findById(Id);
    ArrayList usr = new ArrayList();
    for (User account : users) {
      if (account.getFollowing().contains(user)) {
        usr.add(account);
      }
    }
    return usr;
  }

  @Override
  public void update(User entity) {
    for (int i=0;i<users.size();i++){
      if(users.get(i).getId() == entity.getId()){
        users.set(i,entity);
        break;
      }
    }
  }

  public void setAccounts(CopyOnWriteArrayList<User> accounts) {
    this.users = accounts;
  }
  public void setId(int id){Id = id;}
}

