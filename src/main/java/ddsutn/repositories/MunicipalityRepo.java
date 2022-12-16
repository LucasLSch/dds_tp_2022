package ddsutn.repositories;

import ddsutn.domain.location.Municipality;
import org.springframework.data.repository.CrudRepository;

public interface MunicipalityRepo extends CrudRepository<Municipality, Integer> {
}
