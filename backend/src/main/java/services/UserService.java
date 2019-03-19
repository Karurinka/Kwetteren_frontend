package services;

import DAO.IRoleDAO;
import DAO.IUserDAO;
import com.sun.jersey.spi.inject.Inject;
import logic.models.Role;
import logic.models.User;
import utility.JPA;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService implements Serializable
{
  @Inject
  @JPA
  DAO.IRoleDAO roleDAO;

  @Inject
  @JPA
  DAO.IUserDAO userDAO;

  public User Create(String username, String password, String location, String website, String biography)
  {
    User user = new User(username, password, location, website, biography);
    user.setPassword(hashPassword(password));
    userDAO.create(user);
    return user;
  }

  public boolean Update(int id, String username, String bio, String location, String website)
  {
    try {
      User user = userDAO.findById(id);
      user.setUsername(username);
      user.setBiography(bio);
      user.setLocation(location);
      user.setWebsite(website);
      userDAO.update(user);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean remove(int id) {
    try {
      User account = userDAO.findById(id);
      userDAO.delete(account);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  public void setAccountDAO(IUserDAO accountDAO) {
    this.userDAO = accountDAO;
  }

  public void setTypeDAO(IRoleDAO typeDAO) {
    this.roleDAO = typeDAO;
  }

  public List<User> getAllAccounts() {
    return userDAO.getAll();
  }

  public User findByID(int userId) {
    User user = userDAO.findById(userId);
    return user;
  }

  public User findByUsername(String userName) {
    User user = userDAO.findByUserName(userName);
    return user;
  }

  public List<User> Search(String username)
  {
    List<User> users = userDAO.search(username);
    return users;
  }

  public List<User> getFollowing(int userId)
  {
    return userDAO.getFollowing(userId);
  }

  public List<User> followers(int id) {
    return userDAO.getFollowing(id);
  }

  public boolean addRole(String role, int id)
  {
    try {
      Role type = roleDAO.findOrCreate(role);
      userDAO.addRole(type, id);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  public String hashPassword(String password) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    md.update(password.getBytes());

    byte byteData[] = md.digest();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}
