package Kwetter.DAO.JPAImpl;

import Kwetter.DAO.DAOFacade;
import Kwetter.DAO.IKweetDAO;
import Kwetter.Models.Kweet;
import Kwetter.utility.HibernateSessionFactory;
import Kwetter.utility.JPA;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Named
@Stateless
@JPA
public class KweetDAOJPAImpl extends DAOFacade<Kweet> implements IKweetDAO
{
  @Inject
  private HibernateSessionFactory mySessionFactory;

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
  public HibernateSessionFactory getHibernateSessionFactory()
  {
    return mySessionFactory;
  }

  public void setHibernateSessionFactory(HibernateSessionFactory em)
  {
    this.mySessionFactory = mySessionFactory;
  }

  @Override
  public List<Kweet> getPersonalKweets(int userId)
  {
    Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("kweetdao.getPersonalKweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> getPostedKweets(int userId)
  {
    Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("kweetdao.getPostedKweets");
    q.setParameter("id", userId);
    List result = q.getResultList();
    return result;
  }

  @Override
  public List<Kweet> search(String content)
  {
    Query q = getHibernateSessionFactory().getCurrentSession().createNamedQuery("kweetdao.search");
    q.setParameter("content", "%" + content + "%");
    List result = q.getResultList();
    return result;
  }
}
