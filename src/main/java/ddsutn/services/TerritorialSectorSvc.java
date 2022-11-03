package ddsutn.services;

import ddsutn.domain.territories.TerritorialSector;
import ddsutn.repositories.TerritorialSectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TerritorialSectorSvc extends GenericSvcImpl<TerritorialSector, Long> {

  @Autowired
  private TerritorialSectorRepo territorialSectorRepo;

  @Override
  public CrudRepository<TerritorialSector, Long> getRepo() {
    return this.territorialSectorRepo;
  }

}
