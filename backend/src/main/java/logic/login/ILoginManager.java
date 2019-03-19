package logic.login;

public interface ILoginManager<T>
{
  /**
   * Logs the models into the system
   * @param email to identify the models
   * @param password to check the models credentials
   * @return Returns the session id linked to the models
   */
  int login(String email, char[] password);

  /**
   * Logs the models out of the system
   * @param sessionId by which the models is identified
   */
  void logout(int sessionId);
}
