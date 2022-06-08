package repositories;

import domain.organization.Sector;

public class SectorRepo extends CRUDImpl<Sector> {

  // --- Singleton --- //

  private static SectorRepo instance = null;

  private SectorRepo(){};

  public static SectorRepo getInstance() {
    if(instance == null) {
      instance = new SectorRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
