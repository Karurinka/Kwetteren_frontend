package Kwetter.DAO.DAO_COL;

import Kwetter.DAO.IRoleDAO;
import Kwetter.Models.Role;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Default
public class RoleDAOCol implements IRoleDAO
{
  List<Role> roleList = new ArrayList<>();
  @Override
  public Role findOrCreate(String name) {
    for (Role r : roleList){
      if(r.getGroupName() ==name){
        return r;
      }
    }
    Role type = new Role(name);
    roleList.add(type);
    return type;
  }

}
