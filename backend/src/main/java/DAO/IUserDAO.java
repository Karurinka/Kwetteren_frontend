package DAO;

import DAO.JPAImpl.IKwetterDAO;
import logic.models.Role;
import logic.models.User;

import java.util.List;

public interface IUserDAO extends IKwetterDAO<User>
{
  User findByUserName(String name);
  List<User> search(String name);
  List<User> getFollowing(int Id);
  boolean addRole(Role role, int Id);
  boolean removeRole(Role role, int Id);
}

