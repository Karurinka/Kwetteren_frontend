package Kwetter.endpoints.controllers;

import Kwetter.Bool;
import Kwetter.Models.Kweet;
import Kwetter.Models.User;
import Kwetter.services.UserService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/users")
public class UserController
{

  @Inject
  UserService userService;

  @Context
  UriInfo uriInfo;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String create(String userJson)
  {
    Gson gson = new Gson();
    User user = gson.fromJson(userJson, User.class);
    user = userService.create(user.getUsername(), user.getPassword(), user.getLocation(), user.getWebsite(), user.getBiography());
    if (user != null)
    {
      return "account created with username: " + user.getUsername();
    } else {
      return "user is null.";
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
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
    return Response.ok().entity(users).build();
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
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
  @Produces(MediaType.APPLICATION_JSON)
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
  @Produces(MediaType.APPLICATION_JSON)
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

  @POST
  @Path("/search")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String search(String name)
  {
    Gson gson = new Gson();
    return gson.toJson(userService.search(name));
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
  @Produces(MediaType.APPLICATION_JSON)
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
  @Path("/role/add/{role}/{Id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response RoleAdd(@PathParam("role") String role, @PathParam("Id") int id)
  {
    boolean success;
    try
    {
      success = userService.addRole(role, id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();

  }

  @GET
  @Path("/role/remove/{role}/{Id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response RoleRemove(@PathParam("role") String role, @PathParam("Id") int id)
  {
    boolean success;
    try
    {
      success = userService.removeRole(role, id);
    } catch (Exception e)
    {
      return Response.serverError().build();
    }
    return Response.ok().entity(new Bool(success)).build();
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
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
  @Produces(MediaType.APPLICATION_JSON)
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
