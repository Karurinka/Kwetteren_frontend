package Kwetter.DAO;

import Kwetter.utility.HibernateSessionFactory;

import java.util.List;

public interface IKwetterDAO<T>
{
  HibernateSessionFactory getHibernateSessionFactory();

  List<T> getAll();

  T findById(int id);

  void create(T entity);

  void update(T entity);

  void delete(T entity);
}
