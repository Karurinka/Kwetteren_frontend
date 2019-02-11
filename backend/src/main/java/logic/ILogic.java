package logic;

import logic.user.Kweet;
import logic.user.User;

import java.util.List;

public interface ILogic
{

  //user specific
  int login(String username, char[] password);
  void logout(int sessionId);
  Boolean ChangeUsername(String oldUsername, String newUsername, char[] password);
  Boolean ChangeBiography(String biography, char[] password);
  Kweet GetLatest10Kweets();
  Boolean FollowUser(User follower, User following);

  //kweet specific
  Boolean PostKweet(String username, String content);
  Boolean DeleteKweet();
  List<Kweet> SearchKweet();
}
