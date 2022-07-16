package repositories;

import domain.measurements.ActivityData;

public class ActivityDataRepo extends CrudImpl<ActivityData> {

  // --- Singleton --- //

  private static ActivityDataRepo instance = null;

  private ActivityDataRepo(){
  }

  public static ActivityDataRepo getInstance() {
    if (instance == null) {
      instance = new ActivityDataRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
