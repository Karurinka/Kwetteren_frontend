package logic.login;

public class LoginManager implements ILoginManager
{
  @Override
  public int login(String username, char[] password)
  {
    if (username != null && !username.isEmpty() && password != null)
    {
      //TODO: create session manager and main.java.DAO layer
      //User user = main.java.DAO.login(email, password);

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
