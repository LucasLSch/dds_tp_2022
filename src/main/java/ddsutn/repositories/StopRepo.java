package ddsutn.repositories;

import ddsutn.domain.journey.transport.Stop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepo extends CrudRepository<Stop, Long> {

}
