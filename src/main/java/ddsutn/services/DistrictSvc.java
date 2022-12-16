package ddsutn.services;

import ddsutn.domain.location.District;
import ddsutn.repositories.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictSvc extends GenericSvcImpl<District, Integer> {

  @Autowired
  private DistrictRepo districtRepo;

  @Override
  public CrudRepository<District, Integer> getRepo() {
    return this.districtRepo;
  }

}