package ddsutn.repositories;

import ddsutn.domain.journey.Leg;

public class LegRepo extends CrudImpl<Leg> {

  // --- Singleton --- //

  private static LegRepo instance = null;

  private LegRepo() {
    try {
      this.type = (Class<Leg>) Class.forName("ddsutn.domain.journey.Leg");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
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
