package ddsutn.repositories;

import ddsutn.domain.measurements.ActivityData;

public class ActivityDataRepo extends CrudImpl<ActivityData> {

  // --- Singleton --- //

  private static ActivityDataRepo instance = null;

  private ActivityDataRepo() {
    try {
      this.type = (Class<ActivityData>) Class.forName("ddsutn.domain.measurements.ActivityData");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
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
