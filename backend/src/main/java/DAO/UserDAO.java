package main.java.DAO;

import main.java.logic.user.User;

public interface UserDAO
{
  void updateUser(User user);
  void createUser(User user);
}
