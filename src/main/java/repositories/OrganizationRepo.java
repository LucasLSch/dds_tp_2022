package repositories;

import domain.organization.Organization;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class OrganizationRepo extends CrudImpl<Organization> {

  private EntityManager em;

  // --- Singleton --- //

  private static OrganizationRepo instance = null;

  private OrganizationRepo(){
  }

  public static OrganizationRepo getInstance() {
    if (instance == null) {
      instance = new OrganizationRepo();
      instance.initSavedEntities();
    }
    return instance;
  }


  public List getAllDB() {
    this.em = PerThreadEntityManagers.getEntityManager();
    Query q = em.createQuery("from Organization");
    return q.getResultList();
  }
}
