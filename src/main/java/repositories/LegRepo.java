package repositories;

import domain.journey.Leg;
import security.user.User;

public class LegRepo extends CrudImpl<Leg> {

  // --- Singleton --- //

  private static LegRepo instance = null;

  private LegRepo() {
    try {
      this.type = (Class<Leg>) Class.forName("domain.journey.Leg");
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
