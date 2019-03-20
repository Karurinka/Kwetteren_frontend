package Kwetter.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement
public class Role implements Serializable {
  @Id
  private String groupName;

  @ManyToMany
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
    inverseJoinColumns = @JoinColumn(name = "userName", referencedColumnName = "userName"))
  @JsonBackReference
  private List<User> users;

  public Role() {}

  public Role(String name) {
    this.groupName = name;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  @XmlTransient
  public List<User> getAccounts() {
    return users;
  }

  public void setAccounts(List<User> accounts) {
    this.users = accounts;
  }
}
