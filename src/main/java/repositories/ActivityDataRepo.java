package repositories;

import domain.measurements.ActivityData;

public class ActivityDataRepo extends CrudImpl<ActivityData> {

  // --- Singleton --- //

  private static ActivityDataRepo instance = null;

  private ActivityDataRepo() {
    this.type = new ActivityData();
  }

  public static ActivityDataRepo getInstance() {
    if (instance == null) {
      instance = new ActivityDataRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(ActivityData someEntity) {
    return someEntity.getId();
  }
}
