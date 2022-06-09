package repositories;

import domain.organization.Organization;

public class OrganizationRepo extends CrudImpl<Organization> {

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

}
