package repositories;

import domain.measurments.ConsumptionType;

public class ConsumptionTypeRepo extends CrudImpl<ConsumptionType> {

  // --- Singleton --- //

  private static ConsumptionTypeRepo instance = null;

  private ConsumptionTypeRepo(){
  }

  public static ConsumptionTypeRepo getInstance() {
    if (instance == null) {
      instance = new ConsumptionTypeRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
