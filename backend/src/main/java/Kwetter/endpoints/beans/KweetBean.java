package Kwetter.endpoints.beans;

import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.services.KweetService;

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

  public String Create(User currentUser)
  {
    //TODO: create the right context
    return "app/index";
  }

  public List<Kweet> getAll() {
    return kweetService.getAllTweets();
  }
}
