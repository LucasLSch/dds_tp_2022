package repositories;

import domain.organization.Sector;

public class SectorRepo extends CrudImpl<Sector> {

  // --- Singleton --- //

  private static SectorRepo instance = null;

  private SectorRepo() {
    this.type = new Sector();
  }

  public static SectorRepo getInstance() {
    if (instance == null) {
      instance = new SectorRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
