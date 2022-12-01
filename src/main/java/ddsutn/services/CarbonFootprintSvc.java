package ddsutn.services;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.repositories.CarbonFootprintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class CarbonFootprintSvc extends GenericSvcImpl<CarbonFootprint, Long> {

  @Autowired
  private CarbonFootprintRepo carbonFootprintRepo;

  @Override
  public CrudRepository<CarbonFootprint, Long> getRepo() {
    return carbonFootprintRepo;
  }

  public List<CarbonFootprint> findByOrganization(Long id) {
    return this.carbonFootprintRepo.findByOrganization(id);
  }

  public List<CarbonFootprint> findByMember(Long id) {
    return this.carbonFootprintRepo.findByMember(id);
  }

  public List<CarbonFootprint> findByActivityData(Long id) {
    return this.carbonFootprintRepo.findByActivityData(id);
  }

  public List<CarbonFootprint> findByTerritorialSector(Long id) {
    return this.carbonFootprintRepo.findByTerritorialSector(id);
  }


}
