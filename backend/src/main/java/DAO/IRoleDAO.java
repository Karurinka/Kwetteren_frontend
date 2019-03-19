package DAO;

import logic.models.Role;

public interface IRoleDAO
{
  Role findOrCreate(String name);
}
