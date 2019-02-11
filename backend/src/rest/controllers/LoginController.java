package rest.controllers;

import org.springframework.web.bind.annotation.*;
import rest.models.LoginRequestModel;
import rest.models.LoginResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class LoginController
{
  private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

  @CrossOrigin(origins = "http://localhost:8080")
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public void LogOut(@RequestBody int sessionId)
  {
    //TODO: implement session id
    String userLogoutLogMessage = String.format("User by the Id of %s, attempted to log out", sessionId);
    LOGGER.log(Level.INFO, userLogoutLogMessage);
  }

  @CrossOrigin(origins = "http://localhost:8080")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public LoginResponseModel LogIn(@RequestBody LoginRequestModel loginstream, HttpServletRequest request, HttpServletResponse response)
  {
    if (loginstream.getUsername() == null || loginstream.getPassword() == null)
    {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return new LoginResponseModel(-2, "");
    }

    //TODO: implement session id
    String userLoginLogMessage = String.format("User %s, attempted to log in", loginstream.getUsername());
    LOGGER.log(Level.INFO, userLoginLogMessage);
    //int sessionId = logic.login(loginstream.getEmail(), loginstream.getPassword().toCharArray());
    int sessionId = 1;
    LoginResponseModel loginResponse;

    if (sessionId == -1 || sessionId == 0)
    {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      loginResponse = new LoginResponseModel(sessionId, "");
    }
    else
    {
      response.setStatus(HttpServletResponse.SC_OK);
      //TODO: get username from data layer
      //loginResponse = new LoginResponseModel(sessionId, username);
      loginResponse = new LoginResponseModel();
    }

    return loginResponse;

  }

}
