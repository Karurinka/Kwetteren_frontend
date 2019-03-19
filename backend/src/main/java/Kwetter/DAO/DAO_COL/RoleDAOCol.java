package Kwetter.DAO.DAO_COL;



import Kwetter.DAO.IRoleDAO;
import Kwetter.Models.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Stateless
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
