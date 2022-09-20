package repositories;

import domain.territories.TerritorialSector;

public class TerritorialSectorRepo extends CrudImpl<TerritorialSector> {

  // --- Singleton --- //

  private static TerritorialSectorRepo instance = null;

  private TerritorialSectorRepo() {
    this.type = new TerritorialSector();
  }

  public static TerritorialSectorRepo getInstance() {
    if (instance == null) {
      instance = new TerritorialSectorRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
