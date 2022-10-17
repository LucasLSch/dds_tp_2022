package ddsutn.repositories;

import ddsutn.domain.location.Location;

public class LocationRepo extends CrudImpl<Location> {

  // --- Singleton --- //

  private static LocationRepo instance = null;

  private LocationRepo() {
    try {
      this.type = (Class<Location>) Class.forName("ddsutn.domain.location.Location");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static LocationRepo getInstance() {
    if (instance == null) {
      instance = new LocationRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Location someEntity) {
    return someEntity.getId();
  }
}