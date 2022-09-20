package repositories;

import domain.journey.Leg;

public class LegRepo extends CrudImpl<Leg> {

  // --- Singleton --- //

  private static LegRepo instance = null;

  private LegRepo() {
    this.type = new Leg();
  }

  public static LegRepo getInstance() {
    if (instance == null) {
      instance = new LegRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Leg someEntity) {
    return someEntity.getId();
  }
}
