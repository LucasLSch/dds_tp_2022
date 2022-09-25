package repositories;

import domain.organization.Organization;

import javax.persistence.EntityManager;

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

  @Override
  Object getId(Organization someEntity) {
    return someEntity.getId();
  }
}
