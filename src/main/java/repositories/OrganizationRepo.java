package repositories;

import domain.organization.Organization;
import security.user.User;

import javax.persistence.EntityManager;

public class OrganizationRepo extends CrudImpl<Organization> {

  private EntityManager em;

  // --- Singleton --- //

  private static OrganizationRepo instance = null;

  private OrganizationRepo() {
    try {
      this.type = (Class<Organization>) Class.forName("domain.organization.Organization");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static OrganizationRepo getInstance() {
    if (instance == null) {
      instance = new OrganizationRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Organization someEntity) {
    return someEntity.getId();
  }
}
