package Kwetter.DAO.JPAImpl;

import Kwetter.DAO.DAOFacade;
import Kwetter.DAO.IKweetDAO;
import Kwetter.Models.Kweet;
import Kwetter.utility.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
@JPA
public class KweetDAOJPAImpl extends DAOFacade<Kweet> implements IKweetDAO
{
  @PersistenceContext
  EntityManager em;

  public KweetDAOJPAImpl()
  {
    super(Kweet.class);
  }

  @Override
  public void create(Kweet kweet)
  {
    kweet.setDate(new Date());
    super.create(kweet);
  }

  @Override
  public EntityManager getEntityManager()
  {
    return em;
  }

  public void setEntityManager(EntityManager em)
  {
    this.em = em;
  }

  @Override
  public List<Kweet> getPersonalKweets(int userId)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.getPersonalTweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> getPostedKweets(int userId)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.getPostedTweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> search(String content)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.search");
    q.setParameter("content", "%" + content + "%");
    List result = q.getResultList();
    return result;
  }
}
