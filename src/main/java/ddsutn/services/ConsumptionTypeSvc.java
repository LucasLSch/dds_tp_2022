package ddsutn.services;

import ddsutn.domain.measurements.ConsumptionType;
import ddsutn.repositories.ConsumptionTypeRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionTypeSvc {

  @Autowired
  private ConsumptionTypeRepo consumptionTypeRepo;

  public void saveAll(List<ConsumptionType> consumptionTypeList) {
    this.consumptionTypeRepo.saveAll(consumptionTypeList);
  }
}
