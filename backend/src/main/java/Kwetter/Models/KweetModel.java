package Kwetter.Models;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class KweetModel implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;

  public int getId()
  {
    return Id;
  }

  public void setId(int id)
  {
    Id = id;
  }
}
