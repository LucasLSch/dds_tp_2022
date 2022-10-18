package ddsutn.repositories;

import ddsutn.domain.measurements.CarbonFootprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonFootprintRepo extends CrudRepository<CarbonFootprint, Long> {

}
