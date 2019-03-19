package DAO;

import DAO.JPAImpl.IKwetterDAO;
import logic.models.Kweet;

import java.util.List;

public interface IKweetDAO<T> extends IKwetterDAO<Kweet>
{
  List<Kweet> getPersonalKweets(int userId);
  List<Kweet> getPostedKweets(int userId);
  List<Kweet> search(String content);
}
