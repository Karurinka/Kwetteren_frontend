package logic.login;

import logic.user.User;

public class LoginManager implements ILoginManager
{
  @Override
  public int login(String username, char[] password)
  {
    if (username != null && !username.isEmpty() && password != null)
    {
      //TODO: create session manager and data layer
      //User user = data.login(email, password);

      //if (user != null)
      //{
      //  return sessionManager.getNewSessionID(user);
      //}
    }

    return -1;
  }

  @Override
  public void logout(int sessionId)
  {

  }
}
