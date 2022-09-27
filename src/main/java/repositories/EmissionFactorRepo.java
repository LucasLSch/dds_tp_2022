package repositories;

import domain.measurements.EmissionFactor;
import security.user.User;

public class EmissionFactorRepo extends CrudImpl<EmissionFactor> {

  // --- Singleton --- //

  private static EmissionFactorRepo instance = null;

  private EmissionFactorRepo() {
    try {
      this.type = (Class<EmissionFactor>) Class.forName("domain.measurements.EmissionFactor");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static EmissionFactorRepo getInstance() {
    if (instance == null) {
      instance = new EmissionFactorRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(EmissionFactor someEntity) {
    return someEntity.getId();
  }
}
