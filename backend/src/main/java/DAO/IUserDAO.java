package DAO;

import logic.user.Role;
import logic.user.User;

import java.util.List;

public interface IUserDAO
{
  void update(User user);
  void create(User user);
  User findById(int userId);
  User search(String username);
  List<User> getFollowing(int userId);
  boolean addRole(Role role, int userId);
}
