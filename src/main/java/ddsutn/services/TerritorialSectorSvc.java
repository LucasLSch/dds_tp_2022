package ddsutn.services;

import ddsutn.domain.journey.transport.Transport;
import ddsutn.domain.territories.TerritorialSector;
import ddsutn.repositories.TerritorialSectorRepo;
import ddsutn.repositories.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TerritorialSectorSvc {

  @Autowired
  private TerritorialSectorRepo territorialSectorRepo;

  public void saveAll(List<TerritorialSector> territorialSectors) {
    this.territorialSectorRepo.saveAll(territorialSectors);
  }

}
