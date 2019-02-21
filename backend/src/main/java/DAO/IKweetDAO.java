package DAO;

import logic.user.Kweet;

import java.util.List;

public interface IKweetDAO
{
  void create(Kweet kweet);
  void update(Kweet kweet);
  void delete(Kweet kweet);
  Kweet findById(int kweetId);
  List<Kweet> getAllKweets();
  List<Kweet> getPostedKweets(int userId);
  List<Kweet> getPersonalKweets(int userId);
  List<Kweet> search(String content);
}
