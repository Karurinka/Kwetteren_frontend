package logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.List;

public class Kweet
{
  //variables
  private Date date;
  private String content;
  private Integer maxValue;

  //constructor
  public Kweet(Date date, String content)
  {
    this.date = date;
    this.content = content;
    maxValue = 140;
  }

  //getters
  public Date getDate()
  {
    return date;
  }

  public String getContent()
  {
    return content;
  }

  //setters
  public void setDate(Date date)
  {
    this.date = date;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  //methods
  public Boolean DeleteKweet()
  {
    throw new NotImplementedException();
  }

  public List<Kweet> SearchKweet()
  {
      throw new NotImplementedException();
  }
}
