package repositories;

import domain.location.Location;

public class LocationRepo extends CrudImpl<Location> {

  // --- Singleton --- //

  private static LocationRepo instance = null;

  private LocationRepo() {
    this.type = new Location();
  }

  public static LocationRepo getInstance() {
    if (instance == null) {
      instance = new LocationRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}