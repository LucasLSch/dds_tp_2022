package ddsutn.services;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.repositories.CarbonFootprintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class CarbonFootprintSvc extends GenericSvcImpl<CarbonFootprint, Long> {

  @Autowired
  private CarbonFootprintRepo carbonFootprintRepo;

  @Override
  public CrudRepository<CarbonFootprint, Long> getRepo() {
    return carbonFootprintRepo;
  }

}
