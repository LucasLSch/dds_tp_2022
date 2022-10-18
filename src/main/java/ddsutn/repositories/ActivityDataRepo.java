package ddsutn.repositories;

import ddsutn.domain.measurements.ActivityData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDataRepo extends CrudRepository<ActivityData, Long> {

}
