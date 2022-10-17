package ddsutn.repositories;

import ddsutn.domain.territories.TerritorialSector;

public class TerritorialSectorRepo extends CrudImpl<TerritorialSector> {

  // --- Singleton --- //

  private static TerritorialSectorRepo instance = null;

  private TerritorialSectorRepo() {
    try {
      this.type = (Class<TerritorialSector>) Class.forName("ddsutn.domain.territories.TerritorialSector");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static TerritorialSectorRepo getInstance() {
    if (instance == null) {
      instance = new TerritorialSectorRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(TerritorialSector someEntity) {
    return someEntity.getId();
  }
}
