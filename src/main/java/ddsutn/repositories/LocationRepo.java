package ddsutn.repositories;

import ddsutn.domain.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {

}