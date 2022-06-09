package repositories;

import domain.journey.Leg;

public class LegRepo extends CrudImpl<Leg> {

  // --- Singleton --- //

  private static LegRepo instance = null;

  private LegRepo(){
  }

  public static LegRepo getInstance() {
    if (instance == null) {
      instance = new LegRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
