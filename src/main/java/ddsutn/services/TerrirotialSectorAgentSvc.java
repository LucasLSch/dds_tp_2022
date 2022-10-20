package ddsutn.services;

import ddsutn.domain.territories.TerritorialSector;
import ddsutn.domain.territories.TerritorialSectorAgent;
import ddsutn.repositories.TerritorialSectorAgentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TerrirotialSectorAgentSvc {

  @Autowired
  private TerritorialSectorAgentRepo territorialSectorAgentRepo;

  public void saveAll(List<TerritorialSectorAgent> territorialSectorAgents) {
    this.territorialSectorAgentRepo.saveAll(territorialSectorAgents);
  }

  public TerritorialSectorAgent findById(Long id) {
    return this.territorialSectorAgentRepo
            .findById(id)
            .orElse(null);
  }

}
