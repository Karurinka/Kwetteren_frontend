package logic.login;

public interface ILoginManager<T>
{
  /**
   * Logs the user into the system
   * @param email to identify the user
   * @param password to check the user credentials
   * @return Returns the session id linked to the user
   */
  int login(String email, char[] password);

  /**
   * Logs the user out of the system
   * @param sessionId by which the user is identified
   */
  void logout(int sessionId);
}
