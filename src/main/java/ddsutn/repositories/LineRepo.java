package ddsutn.repositories;

import ddsutn.domain.journey.transport.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepo extends CrudRepository<Line, Long> {

}
