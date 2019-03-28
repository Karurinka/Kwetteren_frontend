package Kwetter.DAO.JPAImpl;

import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.utility.DatabaseCleaner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KweetDAOJPAImplTest
{
  private KweetDAOJPAImpl kweetDAO;
  private UserDAOJPAImpl userDAO;
  private User user1;
  private User user2;
  private Kweet kweet;

  public KweetDAOJPAImplTest()
  {
  }

  @Before
  public void setUp()
  {

  }

  @After
  public void tearDown()
  {
  }

  @Test
  public void personalTweets()
  {

  }

  @Test
  public void postedTweets()
  {
    List<Kweet> kweets = kweetDAO.getPostedKweets(user1.getId());
    Assert.assertEquals(1, kweets.size());
  }

  @Test
  public void search()
  {
    List<Kweet> kweets = kweetDAO.search("content");
    Assert.assertEquals(1, kweets.size());
  }
}
