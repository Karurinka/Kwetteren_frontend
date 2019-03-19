package endpoints.beans;

import Models.Kweet;
import Models.User;
import services.KweetService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class KweetBean implements Serializable
{
  @Inject
  KweetService kweetService;

  //TODO: add models service
  //@Inject
  //UserService userService

  public String Create(User currentUser)
  {
    //TODO: create the right context
    //Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    //String newContent = params.get("createF:Content");
    //kweetService.create(newContent, currentUser.getId());
    return "app/index";
  }

  public List<Kweet> getAll() {
    return kweetService.getAllTweets();
  }
}
