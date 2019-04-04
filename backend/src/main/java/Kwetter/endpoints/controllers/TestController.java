package Kwetter.endpoints.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/test")
public class TestController
{
  @GET
  public String getTest()
  {
    return "get gotten";
  }

  @POST
  public String postTest()
  {
    return "post gotten";
  }
}
