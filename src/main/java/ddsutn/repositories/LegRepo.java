package ddsutn.repositories;

import ddsutn.domain.journey.Leg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegRepo extends CrudRepository<Leg, Long> {

}
