package ddsutn.services;

import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.repositories.TerritorialSectorAgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class TerrirotialSectorAgentSvc extends GenericSvcImpl<TerritorialSectorAgent, Long> {

  @Autowired
  private TerritorialSectorAgentRepo territorialSectorAgentRepo;

  @Override
  public CrudRepository<TerritorialSectorAgent, Long> getRepo() {
    return this.territorialSectorAgentRepo;
  }

}
