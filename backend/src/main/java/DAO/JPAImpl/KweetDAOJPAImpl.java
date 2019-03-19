package DAO.JPAImpl;

import DAO.IKweetDAO;
import logic.models.Kweet;
import utility.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@JPA
public class KweetDAOJPAImpl implements IKweetDAO
{
  @PersistenceContext
  private EntityManager em;

  @Override
  public void create(Kweet kweet)
  {
    em.persist(kweet);
  }

  @Override
  public void update(Kweet kweet)
  {
    em.merge(kweet);
  }

  @Override
  public void delete(Kweet kweet)
  {
    em.remove(kweet);
  }

  @Override
  public Kweet findById(int kweetId)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.findById");
    q.setParameter("id", kweetId);
    Kweet result = (Kweet)q.getSingleResult();
    return result;
  }

  @Override
  public List<Kweet> getAllKweets()
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.getAllKweets");
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> getPostedKweets(int userId)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.getPostedKweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> getPersonalKweets(int userId)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.getPersonalKweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> search(String content)
  {
    Query q = getEntityManager().createNamedQuery("kweetdao.search");
    q.setParameter("content", content);
    List result = q.getResultList();
    return result;
  }

  private EntityManager getEntityManager()
  {
    return em;
  }
}
