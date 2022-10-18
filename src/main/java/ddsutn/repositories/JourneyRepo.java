package ddsutn.repositories;

import ddsutn.domain.journey.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepo extends CrudRepository<Journey, Long> {

}
