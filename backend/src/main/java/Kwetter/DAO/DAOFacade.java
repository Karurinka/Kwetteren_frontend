package Kwetter.DAO;

import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class DAOFacade<T>
{

  private final Class<T> entityClass;

  public DAOFacade(Class<T> entityClass)
  {
    this.entityClass = entityClass;
  }

  protected abstract EntityManager getEntityManager();

  public void create(T entity)
  {
    getEntityManager().persist(entity);
  }

  public void update(T entity)
  {
    getEntityManager().merge(entity);
  }

  public void delete(T entity)
  {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  public T findById(int id)
  {
    T entity = null;
    try
    {
      entity = getEntityManager().find(entityClass, id);
    } catch (NotFoundException e)
    {
      System.out.println("Could not find Entity of type " + entityClass.getName() + " with ID :" + id);
    }
    return entity;
  }

  public List<T> getAll()
  {
    return getEntityManager().createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
  }
}
