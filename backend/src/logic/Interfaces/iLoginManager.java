package logic.Interfaces;

public interface iLoginManager<T>
{
  /**
   * Logs the user into the system
   *
   * @param username    to identify the user
   * @param password to check the user credentials
   * @return Returns the session id linked to the user
   */
  int login(String username, String password);

  /**
   * Logs the user out of the system
   */
  void logout();
}
