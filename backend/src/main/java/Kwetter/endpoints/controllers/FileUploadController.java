package Kwetter.endpoints.controllers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/files")
public class FileUploadController
{
  String localFolder = "";

  @POST
  @Path("/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces("application/json")
  public Response uploadFile(File file)
  {
    throw new NotImplementedException();
  }
}
