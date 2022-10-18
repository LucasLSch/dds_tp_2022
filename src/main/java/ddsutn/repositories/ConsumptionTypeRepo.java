package ddsutn.repositories;

import ddsutn.domain.measurements.ConsumptionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionTypeRepo extends CrudRepository<ConsumptionType, Long> {

}
