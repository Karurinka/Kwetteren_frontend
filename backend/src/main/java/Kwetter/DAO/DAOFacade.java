package Kwetter.DAO;

import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class DAOFacade<T>
{

  private final Class<T> entityClass;

  public DAOFacade(Class<T> entityClass)
  {
    this.entityClass = entityClass;
  }

  protected abstract HibernateSessionFactory getHibernateSessionFactory();

  public void create(T entity)
  {
    Session session = getHibernateSessionFactory().getCurrentSession();
    session.getTransaction().begin();
    session.save(entity);
    session.getTransaction().commit();
  }

  public void update(T entity)
  {
    Session session = getHibernateSessionFactory().getCurrentSession();
    session.getTransaction().begin();
    session.update(entity);
    session.getTransaction().commit();
  }

  public void delete(T entity)
  {
    Session session = getHibernateSessionFactory().getCurrentSession();
    session.getTransaction().begin();
    session.delete(entity);
    session.getTransaction().commit();
  }

  public T findById(int id)
  {
    T entity = null;
    try
    {
      entity = getHibernateSessionFactory().getCurrentSession().find(entityClass, id);
    } catch (NotFoundException e)
    {
      System.out.println("Could not find Entity of type " + entityClass.getName() + " with ID :" + id);
    }
    return entity;
  }

  public List<T> getAll()
  {
    return getHibernateSessionFactory().getCurrentSession().createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
  }
}
