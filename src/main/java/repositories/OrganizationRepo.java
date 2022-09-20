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

  private OrganizationRepo() {
    this.type = new Organization();
  }

  public static OrganizationRepo getInstance() {
    if (instance == null) {
      instance = new OrganizationRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
