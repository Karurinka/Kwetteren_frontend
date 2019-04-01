package Kwetter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
public class Role {
  @Id
  private String groupName;

  @ManyToMany
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
    inverseJoinColumns = @JoinColumn(name = "userName", referencedColumnName = "userName"))

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
