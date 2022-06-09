package repositories;

import domain.measurments.EmissionFactor;

public class EmissionFactorRepo extends CrudImpl<EmissionFactor> {

  // --- Singleton --- //

  private static EmissionFactorRepo instance = null;

  private EmissionFactorRepo(){
  }

  public static EmissionFactorRepo getInstance() {
    if (instance == null) {
      instance = new EmissionFactorRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
