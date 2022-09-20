package repositories;

import domain.measurements.EmissionFactor;

public class EmissionFactorRepo extends CrudImpl<EmissionFactor> {

  // --- Singleton --- //

  private static EmissionFactorRepo instance = null;

  private EmissionFactorRepo() {
    this.type = new EmissionFactor();
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
