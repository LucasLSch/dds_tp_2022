package ddsutn.services;

import ddsutn.domain.organization.Organization;
import ddsutn.repositories.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.function.Predicate;

public class OrganizationSvc extends GenericSvcImpl<Organization, Long> {

  @Autowired
  private OrganizationRepo organizationRepo;

  @Override
  public Iterable<Organization> saveAll(List<Organization> entityList) {
    return super.saveAll(entityList);
  }

  @Override
  public List<Organization> findAll() {
    return super.findAll();
  }

  @Override
  public List<Organization> findAllByCondition(Predicate<Organization> predicate) {
    return super.findAllByCondition(predicate);
  }

  @Override
  public Organization findById(Long aLong) {
    return super.findById(aLong);
  }

  @Override
  public Organization save(Organization entity) {
    return super.save(entity);
  }


  @Override
  public CrudRepository<Organization, Long> getRepo() {
    return this.organizationRepo;
  }
}
