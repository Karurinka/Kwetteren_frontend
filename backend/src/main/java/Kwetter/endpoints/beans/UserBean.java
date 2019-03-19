package endpoints.beans;

import Models.User;
import services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class UserBean implements Serializable
{
  @Inject
  UserService userService;
  User loggedIn = null;

  public User getLoggedIn()
  {
    if (loggedIn != null)
    {
      return loggedIn;
    }
    loggedIn = userService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    return loggedIn;
  }

  public User getByUserName(String userName)
  {
    User user = null;
    try
    {
      user = userService.findByUsername(userName);
    } catch (Exception e)
    {
      System.out.println("Could not find user by username :" + userName);
    }
    return user;
  }

  public List<User> getAll()
  {
    return userService.getAllAccounts();
  }

  public void removeUser()
  {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
  }

  public List<User> getFollowing(User selectedAccount)
  {
    return userService.followers(selectedAccount.getId());
  }

  public User follow(User toFollow)
  {
//    public boolean follow(Account toFollow) {
//        try {
    userService.followToggle(toFollow, loggedIn);
//        } catch (Exception e) {
//            System.out.println("Could not follow or unfollow user:" + toFollow.getUserName() + " for loggedIn user :" + loggedIn.getUserName());
//            return false;
//        }
//        return true;
//        return "/app/userprofile?user=" + toFollow.getUserName();
    return toFollow;
  }

  public boolean isFollowing(User toFollow)
  {
    if (loggedIn == null)
    {
      getLoggedIn();
    }
    return loggedIn.getFollowing().contains(toFollow) || loggedIn.getUserName() == toFollow.getUserName();
  }

  public List<User> search(String searchQuery)
  {
    return userService.search(searchQuery);
  }

  public void searchRedirect()
  {
    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String query = params.get("searchF:searchContent");

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    try
    {
      response.sendRedirect("accounts.xhtml?query=" + query);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
