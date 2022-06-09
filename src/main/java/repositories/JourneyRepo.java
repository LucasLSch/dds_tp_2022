package repositories;

import domain.journey.Journey;

public class JourneyRepo extends CrudImpl<Journey> {

  // --- Singleton --- //

  private static JourneyRepo instance = null;

  private JourneyRepo(){
  }

  public static JourneyRepo getInstance() {
    if (instance == null) {
      instance = new JourneyRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
