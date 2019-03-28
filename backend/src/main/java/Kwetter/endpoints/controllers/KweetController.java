package Kwetter.endpoints.controllers;

import Kwetter.Bool;
import Kwetter.Models.Kweet;
import Kwetter.services.KweetService;
import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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
  @Consumes(MediaType.APPLICATION_JSON)
  public void create(String kweetJson)
  {
    Gson gson = new Gson();
    Kweet kweet = gson.fromJson(kweetJson, Kweet.class);
    kweetService.create(kweet.getContent(), kweet.getPostAccount().getId());
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
