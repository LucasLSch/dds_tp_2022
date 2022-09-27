package repositories;

import domain.organization.Sector;
import security.user.User;

public class SectorRepo extends CrudImpl<Sector> {

  // --- Singleton --- //

  private static SectorRepo instance = null;

  private SectorRepo() {
    try {
      this.type = (Class<Sector>) Class.forName("domain.organization.Sector");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static SectorRepo getInstance() {
    if (instance == null) {
      instance = new SectorRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Sector someEntity) {
    return someEntity.getId();
  }
}
