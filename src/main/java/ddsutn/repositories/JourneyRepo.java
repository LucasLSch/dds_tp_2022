package ddsutn.repositories;

import ddsutn.domain.journey.Journey;

public class JourneyRepo extends CrudImpl<Journey> {

  // --- Singleton --- //

  private static JourneyRepo instance = null;

  private JourneyRepo() {
    try {
      this.type = (Class<Journey>) Class.forName("ddsutn.domain.journey.Journey");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static JourneyRepo getInstance() {
    if (instance == null) {
      instance = new JourneyRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Journey someEntity) {
    return someEntity.getId();
  }
}
