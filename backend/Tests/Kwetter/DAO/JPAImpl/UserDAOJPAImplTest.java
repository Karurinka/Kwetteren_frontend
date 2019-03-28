package Kwetter.DAO.JPAImpl;

import Kwetter.Models.Role;
import Kwetter.Models.User;
import Kwetter.utility.DatabaseCleaner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDAOJPAImplTest {
  private EntityTransaction tx;
  private UserDAOJPAImpl userDAO;
  private RoleDAOJPAImpl roleDAO;
  private User user1;
  private User user2;

  public UserDAOJPAImplTest() {
  }

  @Before
  public void setUp() {
    user1 = new User("foo", "", "", "", "");
    user1.setPassword("a");
    user2 = new User("bar", "A", "", "", "");
    user2.setPassword("a");
    tx.begin();
    userDAO.create(user1);
    userDAO.create(user2);
    tx.commit();
    assertThat(userDAO.getAll().size(), is(2));
  }

  @After
  public void tearDown() {
  }

  @Test
  public void findByUserNameTest() {
    User acc = userDAO.findByUserName("foo");
    Assert.assertNotNull(acc);
  }

  @Test
  public void searchTest() {
    List<User> users = userDAO.search("f");
    Assert.assertEquals(1, users.size());
    users = userDAO.search("f o o");
    Assert.assertEquals(1, users.size());
    tx.begin();
    userDAO.delete(user1);
    tx.commit();
    users = userDAO.search("f o o");
    Assert.assertEquals(0, users.size());
  }

  @Test
  public void Following() {
    user1.addFollowing(user2);
    tx.begin();
    userDAO.update(user1);
    tx.commit();
    User usr = userDAO.findById(user1.getId());
    Assert.assertEquals(1, usr.getFollowing().size());
    Assert.assertEquals(1, userDAO.getFollowing(user2.getId()).size());
  }

  @Test
  public void Roles() {
    tx.begin();
    Role role = roleDAO.findOrCreate("test");
    userDAO.addRole(role, user1.getId());
    tx.commit();
    Assert.assertEquals(1, userDAO.findById(user1.getId()).getGroups().size());
    tx.begin();
    userDAO.removeRole(role, user1.getId());
    tx.commit();
    Assert.assertEquals(0, user1.getGroups().size());
  }
}
