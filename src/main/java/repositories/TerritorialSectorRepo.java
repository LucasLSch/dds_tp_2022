package repositories;

import domain.territories.TerritorialSector;
import security.user.User;

public class TerritorialSectorRepo extends CrudImpl<TerritorialSector> {

  // --- Singleton --- //

  private static TerritorialSectorRepo instance = null;

  private TerritorialSectorRepo() {
    try {
      this.type = (Class<TerritorialSector>) Class.forName("domain.territories.TerritorialSector");
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
