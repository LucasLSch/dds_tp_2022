package ddsutn.repositories;

import ddsutn.domain.organization.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Long> {

}
