package repositories;

import domain.measurements.unit.Unit;

public class SimpleUnitRepo extends CrudImpl<Unit> {

  // --- Singleton --- //

  private static SimpleUnitRepo instance = null;

  private SimpleUnitRepo() {
    this.type = new Unit();
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
