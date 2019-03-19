package Kwetter.DAO;

import javax.persistence.EntityManager;
import java.util.List;

public interface IKwetterDAO<T>
{
  EntityManager getEntityManager();
  List<T> getAll();
  T findById(int id);
  void create(T entity);
  void update(T entity);
  void delete(T entity);
}
