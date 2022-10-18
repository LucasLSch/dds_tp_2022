package ddsutn.repositories;

import ddsutn.domain.territories.TerritorialSectorAgent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritorialSectorAgentRepo extends CrudRepository<TerritorialSectorAgent, Long> {

}
