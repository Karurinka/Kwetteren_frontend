package Kwetter.REST;

import Kwetter.DAO.DAO_COL.UserDAOCol;
import Kwetter.DAO.IUserDAO;
import Kwetter.Models.User;
import Kwetter.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class UserControllerTests
{
  private UserService userService;
  private IUserDAO userDAO;
  private List<User> users;
  private User user;

  @Before
  public void setUp() {
    userService = new UserService();
    userDAO = new UserDAOCol();
    user = new User();
  }

  @Test
  public void createUser(){

  }

  @Test
  public void getAllUsers()
  {
    userService.setAccountDAO(userDAO);
    userService.create("user1", "test1", "location", "website", "bio");
    user = userService.findByUsername("user1");

    Assert.assertNotNull(user);
    Assert.assertEquals(0, user.getId());

    users = userService.search("u");
    Assert.assertTrue(users.contains(user));

    userService.getAllAccounts();
    System.out.println(users);
  }
}
