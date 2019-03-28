package Kwetter.DAO.JPAImpl;

import Kwetter.DAO.IRoleDAO;
import Kwetter.Models.Role;
import Kwetter.utility.JPA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
@JPA
public class RoleDAOJPAImpl implements IRoleDAO {
  @PersistenceContext
  EntityManager em;

  @Override
  public Role findOrCreate(String name) {
    Role entity;
    try {
      entity = em.find(Role.class, name);
      return entity;
    } catch (Exception e) {
      entity = new Role(name);
      em.persist(entity);
      return entity;
    }
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }
}
