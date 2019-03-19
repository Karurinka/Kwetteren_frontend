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
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
  private EntityManager em;
  private EntityTransaction tx;
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
    try
    {
      new DatabaseCleaner(emf.createEntityManager()).clean();
    } catch (SQLException ex)
    {
      Logger.getLogger(UserDAOJPAImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    em = emf.createEntityManager();
    tx = em.getTransaction();

    kweetDAO = new KweetDAOJPAImpl();
    kweetDAO.setEntityManager(em);

    userDAO = new UserDAOJPAImpl();
    userDAO.setEm(em);


    user1 = new User("foo", "", "", "", "");
    user1.setPassword("a");
    user2 = new User("bar", "A", "", "", "");
    user2.setPassword("a");
    tx.begin();
    userDAO.create(user1);
    userDAO.create(user2);
    tx.commit();
    assertThat(userDAO.getAll().size(), is(2));

    tx.begin();
    kweet = new Kweet("content", user1);
    kweetDAO.create(kweet);
    tx.commit();
  }

  @After
  public void tearDown()
  {
  }

  @Test
  public void personalTweets()
  {
    tx.begin();
    user2.addFollowing(user1);
    userDAO.update(user2);
    tx.commit();
    List<Kweet> kweets = kweetDAO.getPersonalKweets(user2.getId());
    Assert.assertEquals(1, kweets.size());

    tx.begin();
    kweet = new Kweet("Content fom account2", user2);
    kweetDAO.create(kweet);
    tx.commit();
    kweets = kweetDAO.getPersonalKweets(user2.getId());
    Assert.assertEquals(2, kweets.size());
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
