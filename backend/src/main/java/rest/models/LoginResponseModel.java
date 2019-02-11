package rest.models;

public class LoginResponseModel
{
  private int sessionId;
  private String username;

  public LoginResponseModel(int sessionId, String username)
  {
    this.sessionId = sessionId;
    this.username = username;
  }

  public LoginResponseModel()
  {
  }

  //getters
  public int getSessionId()
  {
    return sessionId;
  }

  public String getUsername() {
    return username;
  }

  //setters
  public void setSessionId(int sessionId)
  {
    this.sessionId = sessionId;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
