package Kwetter.DAO;

import Kwetter.Models.Kweet;

import java.util.List;

public interface IKweetDAO extends IKwetterDAO<Kweet>
{
  List<Kweet> getPersonalKweets(int userId);

  List<Kweet> getPostedKweets(int userId);

  List<Kweet> search(String content);
}
