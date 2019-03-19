package Kwetter.endpoints.controllers;

import Kwetter.Bool;
import Kwetter.Models.Kweet;
import Kwetter.endpoints.beans.JMSSender;
import Kwetter.services.KweetService;
import Kwetter.utility.JMSMessenger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Path("/kweets")
public class KweetController
{
  @Inject
  KweetService kweetService;
  @Context
  UriInfo uriInfo;

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces("application/json")
  public Response create(@FormParam("content") String content, @FormParam("userId") int userId)
  {
    Kweet kweet;
    try
    {
      kweet = kweetService.create(content, userId);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (kweet == null) return Response.serverError().build();
    return Response.created(getCreatedLink(kweet)).entity(kweet).build();
  }


  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Response getById(@PathParam("id") int id)
  {
    Kweet kweet;
    try
    {
      kweet = kweetService.findById(id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (kweet == null) return Response.serverError().build();
    return Response.ok().entity(kweet).build();
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Path("/jms")
  @Produces("application/json")
  public Response jms(@FormParam("id") int id, @FormParam("content") String content)
  {
    new JMSSender().send(id + "," + content);
    return Response.ok().build();
  }


  @GET
  @Path("/all")
  @Produces("application/json")
  public Response getAllTweets()
  {
    List<Kweet> kweets;
    try
    {
      kweets = kweetService.getAllTweets();
    } catch (Exception e)
    {
      return Response.serverError().build();
    }

    final GenericEntity<List<Kweet>> entity = new GenericEntity<List<Kweet>>(kweets)
    {};
    return Response.ok().entity(entity).build();
  }

  @GET
  @Produces("application/json")
  public Response getPersonalTweets(@QueryParam("id") int id)
  {
    List<Kweet> kweets;
    try
    {
      kweets = kweetService.getPersonalTweets(id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    final GenericEntity<List<Kweet>> entity = new GenericEntity<List<Kweet>>(kweets)
    {};
    return Response.ok().entity(entity).build();
  }


  @GET
  @Path("/search")
  @Produces("application/json")
  public Response search(@QueryParam("content") String content)
  {
    List<Kweet> kweets;
    try
    {
      kweets = kweetService.search(content);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    final GenericEntity<List<Kweet>> entity = new GenericEntity<List<Kweet>>(kweets)
    {};
    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/remove/{Id}")
  @Produces("application/json")
  public Response delete(@PathParam("Id") int id)
  {
    boolean success;
    try
    {
      success = kweetService.remove(id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();
  }

  //@GET
  //@Path("/heart/{Id}")
  //@Produces("application/json")
  //public Response heart(@PathParam("Id") int id, @QueryParam("userId") int userId)
  //{
  //  boolean success;
  //  try
  //  {
  //    success = kweetService.hearth(id, userId);
  //  } catch (Exception e)
  //  {
  //    return Response.serverError().build();
  //  }
  //  return Response.ok().entity(new Bool(success)).build();
  //}

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces("application/json")
  @Path("/edit")
  public Response edit(@QueryParam("id") int id, @FormParam("content") String content)
  {
    Kweet kweet;
    try
    {
      kweet = kweetService.edit(id, content);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(kweet).build();
  }


  private URI getCreatedLink(Kweet entity)
  {

    return uriInfo.getAbsolutePathBuilder().path(entity.getId() + "").build();
  }
}
