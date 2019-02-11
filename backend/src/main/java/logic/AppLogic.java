package logic;

import logic.login.ILoginManager;
import logic.profile.IProfileManager;
import logic.user.Kweet;
import logic.user.User;

import java.util.List;
import java.util.logging.Logger;

public class AppLogic implements ILogic
{

  private static final Logger LOGGER = Logger.getLogger(AppLogic.class.getName());
  private ILoginManager<User> loginManager;
  private IProfileManager profileManager;

  public AppLogic(ILoginManager<User> loginManager, IProfileManager profileManager)
  {
    this.loginManager = loginManager;
    this.profileManager = profileManager;
  }

  @Override
  public int login(String username, char[] password)
  {
    return loginManager.login(username, password);
  }

  @Override
  public void logout(int sessionId)
  {
    loginManager.logout(sessionId);
  }

  @Override
  public Boolean ChangeUsername(String oldUsername, String newUsername, char[] password)
  {
    return profileManager.ChangeUsername(oldUsername, newUsername, password);
  }

  @Override
  public Boolean ChangeBiography(String biography, char[] password)
  {
    return profileManager.ChangeBiography(biography, password);
  }

  @Override
  public Kweet GetLatest10Kweets()
  {
    return null;
  }

  @Override
  public Boolean FollowUser(User follower, User following)
  {
    return null;
  }

  @Override
  public Boolean PostKweet(String username, String content)
  {
    return null;
  }

  @Override
  public Boolean DeleteKweet()
  {
    return null;
  }

  @Override
  public List<Kweet> SearchKweet()
  {
    return null;
  }
}
