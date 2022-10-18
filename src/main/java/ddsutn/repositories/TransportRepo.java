package ddsutn.repositories;

import ddsutn.domain.journey.transport.Transport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepo extends CrudRepository<Transport, Long> {

}
