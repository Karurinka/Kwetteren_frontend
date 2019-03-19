package endpoints.controllers;

import services.KweetService;
import com.sun.jersey.spi.inject.Inject;
import logic.models.Kweet;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("/kweets")
public class KweetController
{
  @Inject
  KweetService kweetService;
  @Context
  UriInfo uriInfo;

  //@POST
  //@Consumes("application/x-www-form-urlencoded")
  //@Produces("application/json")
  //public Response create(@FormParam("content") String content,
  //                       @FormParam("userId") int userId) {
  //  Kweet kweet;
  //  try {
  //    kweet = kweetService.create(content, userId);
  //  } catch (Exception e) {
  //    return Response.serverError().build();
  //  }
  //  if (kweet == null) return Response.serverError().build();
  //  return Response.created(getCreatedLink(kweet)).entity(kweet).build();
  //}

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Response getById(@PathParam("id") int id) {
    Kweet kweet;
    try {
      kweet = kweetService.findById(id);
    } catch (Exception e) {
      return Response.serverError().build();
    }
    if (kweet == null) return Response.serverError().build();
    return Response.ok().entity(kweet).build();
  }
  //@POST
  //@Consumes("application/x-www-form-urlencoded")
  //@Path("/jms")
  //@Produces("application/json")
  //public Response jms(@FormParam("id") int id, @FormParam("content") String content) {
  //  new JMSSender().send(id + "," + content);
  //  return Response.ok().build();
  //}


}
