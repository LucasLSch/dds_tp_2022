package repositories;

import domain.journey.transport.Stop;

public class StopRepo extends CRUDImpl<Stop> {

  // --- Singleton --- //

  private static StopRepo instance = null;

  private StopRepo(){};

  public static StopRepo getInstance() {
    if(instance == null) {
      instance = new StopRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
