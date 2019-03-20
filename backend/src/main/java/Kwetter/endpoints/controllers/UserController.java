package Kwetter.endpoints.controllers;


import Kwetter.Bool;
import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.services.UserService;

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
@Path("/users")
public class UserController
{

  @Inject
  UserService userService;

  @Context
  UriInfo uriInfo;

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces("application/json")
  public Response create(@FormParam("username") String username, @FormParam("password") String password, @FormParam("location") String location, @FormParam("website") String website, @FormParam("bio") String bio)
  {
    User user;
    try
    {
      user = userService.create(username, password, location, website, bio);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (user == null) return Response.serverError().build();
    return Response.created(getCreatedLink(user)).entity(user).build();
  }

  @GET
  @Produces("application/json")
  public Response getAllAccounts()
  {
    List<User> users;
    try
    {
      users = userService.getAllAccounts();
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users)
    {};
    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/{userId}")
  @Produces("application/json")
  public Response getAccountById(@PathParam("userId") int userId)
  {
    User user;
    try
    {
      user = userService.findByID(userId);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (user == null) return Response.noContent().build();
    return Response.ok().entity(user).build();
  }

  @GET
  @Path("/tweets/{userId}")
  @Produces("application/json")
  public Response getTweetsByAccountId(@PathParam("userId") int userId)
  {
    User account;
    try
    {
      account = userService.findByID(userId);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (account == null || account.getTweets() == null) return Response.noContent().build();
    final GenericEntity<List<Kweet>> entity = new GenericEntity<List<Kweet>>(account.getTweets())
    {};
    return Response.ok().entity(entity).build();
  }


  @GET
  @Path("/username/{userName}")
  @Produces("application/json")
  public Response getAccountByUsername(@PathParam("userName") String userName)
  {
    User user;
    try
    {
      user = userService.findByUsername(userName);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (user == null) return Response.noContent().build();
    return Response.ok().entity(user).build();
  }

  @GET
  @Path("/search/{name}")
  @Produces("application/json")
  public Response search(@PathParam("name") String name)
  {
    List<User> users;
    try
    {
      users = userService.search(name);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (users == null) return Response.serverError().build();
    final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users)
    {};

    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/follow/{Id}")
  @Produces("application/json")
  public Response followToggle(@PathParam("Id") int id, @QueryParam("loggedInUser") int loggedInUser)
  {
    boolean success;
    try
    {
      success = userService.followToggle(id, loggedInUser);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();
  }

  @GET
  @Path("/followers/{Id}")//following you
  @Produces("application/json")
  public Response followers(@PathParam("Id") int id)
  {
    List<User> users;
    try
    {
      users = userService.followers(id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    if (users == null) return Response.serverError().build();
    final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users)
    {};

    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/role/add/{type}/{Id}")
  @Produces("application/json")
  public Response RoleAdd(@PathParam("type") String type, @PathParam("Id") int id)
  {
    boolean success;
    try
    {
      success = userService.addRole(type, id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();

  }

  @GET
  @Path("/role/remove/{type}/{Id}")//following you
  @Produces("application/json")
  public Response RoleRemove(@PathParam("type") String type, @PathParam("Id") int id)
  {
    boolean success;
    try
    {
      success = userService.removeRole(type, id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces("application/json")
  @Path("/edit/password")
  public Response editPassword(@FormParam("currentPass") String currentPass, @FormParam("newPass") String newPass, @QueryParam("id") int id)
  {
    boolean success;
    try
    {
      success = userService.editPassword(id, currentPass, newPass);
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
  public Response edit(@QueryParam("id") int id, @FormParam("username") String username, @FormParam("bio") String bio, @FormParam("location") String location, @FormParam("website") String website)
  {
    boolean success;
    try
    {
      success = userService.update(id, username, bio, location, website);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();
  }


  private URI getCreatedLink(User entity)
  {
    return uriInfo.getAbsolutePathBuilder().path(entity.getId() + "").build();
  }
}
