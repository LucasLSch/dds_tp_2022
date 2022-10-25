package ddsutn.services;

import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.repositories.ConsumptionTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionTypeSvc extends GenericSvcImpl<ConsumptionType, Long> {

  @Autowired
  private ConsumptionTypeRepo consumptionTypeRepo;

  @Override
  public CrudRepository<ConsumptionType, Long> getRepo() {
    return this.consumptionTypeRepo;
  }

}
