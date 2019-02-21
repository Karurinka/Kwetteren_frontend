package services;

import com.sun.jersey.spi.inject.Inject;
import logic.user.User;
import utility.JPA;

import java.io.Serializable;

public class UserService implements Serializable
{
  @Inject
  @JPA
  DAO.IKweetDAO kweetDAO;

  @Inject
  @JPA
  DAO.IUserDAO userDAO;

  public User Create(int userId)
  {
    User user = userDAO.findById(userId);
    userDAO.create(user);
    return user;
  }

  public User Update()
  {
    return null;
  }

  public User Remove()
  {
    return null;
  }
}
