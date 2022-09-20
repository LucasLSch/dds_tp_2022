package repositories;

import domain.journey.transport.Stop;

public class StopRepo extends CrudImpl<Stop> {

  // --- Singleton --- //

  private static StopRepo instance = null;

  private StopRepo() {
    this.type = new Stop();
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
