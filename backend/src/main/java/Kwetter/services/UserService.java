package Kwetter.services;

import Kwetter.DAO.IRoleDAO;
import Kwetter.DAO.IUserDAO;
import Kwetter.Models.Role;
import Kwetter.Models.User;
import Kwetter.utils.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
public class UserService implements Serializable
{
  @Inject
  @JPA
  IRoleDAO roleDAO;

  @Inject
  @JPA
  IUserDAO userDAO;

  public User create(String username, String password, String location, String website, String bio)
  {
    User user = new User(username, password, location, website, bio);
    user.setPassword(hashPassword(password));
    userDAO.create(user);
    return user;
  }

  public boolean update(int id, String username, String bio, String location, String website)
  {
    try
    {
      User user = userDAO.findById(id);
      user.setUsername(username);
      user.setBiography(bio);
      user.setLocation(location);
      user.setWebsite(website);
      userDAO.update(user);
      return true;
    } catch (Exception e)
    {
      return false;
    }
  }

  public boolean remove(int id)
  {
    try
    {
      User account = userDAO.findById(id);
      userDAO.delete(account);
    } catch (Exception e)
    {
      System.out.println(e);
      return false;
    }
    return true;
  }

  public void setAccountDAO(IUserDAO accountDAO)
  {
    this.userDAO = accountDAO;
  }

  public void setTypeDAO(IRoleDAO typeDAO)
  {
    this.roleDAO = typeDAO;
  }

  public List<User> getAllAccounts()
  {
    return userDAO.getAll();
  }

  public User findByID(int userId)
  {
    User user = userDAO.findById(userId);
    return user;
  }

  public User findByUsername(String userName)
  {
    User user = userDAO.findByUserName(userName);
    return user;
  }

  public List<User> search(String username)
  {
    List<User> users = userDAO.search(username);
    return users;
  }

  public boolean followToggle(int toFollowId, int loggedInId)
  {
    User toFollow = userDAO.findById(toFollowId);
    User loggedIn = userDAO.findById(loggedInId);
    return followToggle(toFollow, loggedIn);
  }

  public boolean followToggle(User tofollow, User loggedIn)
  {
    try
    {
      if (loggedIn.getFollowing().contains(tofollow))
      {
        loggedIn.removeFollowing(tofollow);
      }
      else
      {
        loggedIn.addFollowing(tofollow);
      }
      userDAO.update(loggedIn);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public boolean follow(int toFollowId, int loggedInId)
  {
    try
    {
      User toFollow = userDAO.findById(toFollowId);
      User loggedIn = userDAO.findById(loggedInId);
      loggedIn.addFollowing(toFollow);
      userDAO.update(loggedIn);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public List<User> getFollowing(int userId)
  {
    return userDAO.getFollowing(userId);
  }

  public List<User> followers(int id)
  {
    return userDAO.getFollowing(id);
  }

  public boolean unFollow(int toUnfollowId, int loggedInId)
  {
    try
    {
      User toUnfollow = userDAO.findById(toUnfollowId);
      User loggedIn = userDAO.findById(loggedInId);
      loggedIn.removeFollowing(toUnfollow);
      userDAO.update(loggedIn);
    } catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public boolean addRole(String role, int id)
  {
    try
    {
      Role type = roleDAO.findOrCreate(role);
      userDAO.addRole(type, id);
    } catch (Exception e)
    {
      System.out.println(e);
      return false;
    }
    return true;
  }

  public boolean removeRole(String role, int id)
  {
    try
    {
      Role type = roleDAO.findOrCreate(role);
      userDAO.removeRole(type, id);
    } catch (Exception e)
    {
      System.out.println(e);
      return false;
    }
    return true;
  }

  public boolean editPassword(int id, String currentPass, String newPass)
  {
    User user;
    try
    {
      user = findByID(id);
      if (user.getPassword() != hashPassword(currentPass))
      {
        return false;
      }
      user.setPassword(hashPassword(newPass));
      userDAO.update(user);
      return true;
    } catch (Exception e)
    {
      return false;
    }
  }

  public String hashPassword(String password)
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    md.update(password.getBytes());

    byte byteData[] = md.digest();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++)
    {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}
