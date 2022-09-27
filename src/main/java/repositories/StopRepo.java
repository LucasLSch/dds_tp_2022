package repositories;

import domain.journey.transport.Stop;
import security.user.User;

public class StopRepo extends CrudImpl<Stop> {

  // --- Singleton --- //

  private static StopRepo instance = null;

  private StopRepo() {
    try {
      this.type = (Class<Stop>) Class.forName("domain.journey.transport.Stop");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static StopRepo getInstance() {
    if (instance == null) {
      instance = new StopRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Stop someEntity) {
    return someEntity.getId();
  }
}
