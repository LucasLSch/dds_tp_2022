package repositories;

import domain.location.Location;
import security.user.User;

public class LocationRepo extends CrudImpl<Location> {

  // --- Singleton --- //

  private static LocationRepo instance = null;

  private LocationRepo() {
    try {
      this.type = (Class<Location>) Class.forName("domain.location.Location");
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