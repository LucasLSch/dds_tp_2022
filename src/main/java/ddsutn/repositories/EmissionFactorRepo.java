package ddsutn.repositories;

import ddsutn.domain.measurements.EmissionFactor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionFactorRepo extends CrudRepository<EmissionFactor, Long> {

}
