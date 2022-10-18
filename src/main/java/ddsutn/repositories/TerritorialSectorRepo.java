package ddsutn.repositories;

import ddsutn.domain.territories.TerritorialSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritorialSectorRepo extends CrudRepository<TerritorialSector, Long> {

}
