package Kwetter.DAO;

import Kwetter.Models.Role;

public interface IRoleDAO
{
  Role findOrCreate(String name);
}
