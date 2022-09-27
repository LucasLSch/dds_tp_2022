package repositories;

import domain.journey.Journey;
import security.user.User;

public class JourneyRepo extends CrudImpl<Journey> {

  // --- Singleton --- //

  private static JourneyRepo instance = null;

  private JourneyRepo() {
    try {
      this.type = (Class<Journey>) Class.forName("domain.journey.Journey");
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
