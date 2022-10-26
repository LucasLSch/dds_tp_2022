package ddsutn.services;

import ddsutn.domain.organization.Organization;
import ddsutn.repositories.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class OrganizationSvc extends GenericSvcImpl<Organization, Long> {

  @Autowired
  private OrganizationRepo organizationRepo;

  @Override
  public CrudRepository<Organization, Long> getRepo() {
    return this.organizationRepo;
  }
}
