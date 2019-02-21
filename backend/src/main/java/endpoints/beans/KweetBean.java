package endpoints.beans;

import logic.user.Kweet;
import logic.user.User;
import services.KweetService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class KweetBean implements Serializable
{
  @Inject
  KweetService kweetService;

  //TODO: add user service
  //@Inject
  //UserService userService

  public String Create(User currentUser)
  {
    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String newContent = params.get("createF:Content");
    kweetService.create(newContent, currentUser.getId());
    return "app/index";
  }

  public List<Kweet> getAll() {
    return kweetService.getAllTweets();
  }
}
