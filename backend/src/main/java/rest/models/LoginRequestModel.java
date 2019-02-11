package rest.models;

public class LoginRequestModel
{
  //variables
  private String username;
  private String password;

  //constructors
  public LoginRequestModel()
  {

  }

  public LoginRequestModel(String username, String password) {
    this.username = username;
    this.password = password;
  }

  //getters
  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  //setters
  public void setUsername(String username)
  {
    this.username = username;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  @Override
  public String toString()
  {
    return "LoginRequestModel{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
  }
}
