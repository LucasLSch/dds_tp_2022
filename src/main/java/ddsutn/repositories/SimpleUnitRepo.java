package ddsutn.repositories;

import ddsutn.domain.measurements.unit.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleUnitRepo extends CrudRepository<Unit, Long> {

}
