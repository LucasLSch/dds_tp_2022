package repositories;

import domain.measurements.unit.Unit;
import security.user.User;

public class SimpleUnitRepo extends CrudImpl<Unit> {

  // --- Singleton --- //

  private static SimpleUnitRepo instance = null;

  private SimpleUnitRepo() {
    try {
      this.type = (Class<Unit>) Class.forName("domain.measurements.unit.Unit");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static SimpleUnitRepo getInstance() {
    if (instance == null) {
      instance = new SimpleUnitRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Unit someEntity) {
    return someEntity.getId();
  }
}
