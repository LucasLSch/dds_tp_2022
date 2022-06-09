package repositories;

import domain.location.Location;

public class LocationRepo extends CrudImpl<Location> {

  // --- Singleton --- //

  private static LocationRepo instance = null;

  private LocationRepo() {
  }

  public static LocationRepo getInstance() {
    if (instance == null) {
      instance = new LocationRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}