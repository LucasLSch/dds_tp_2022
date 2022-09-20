package repositories;

import domain.measurements.ConsumptionType;

public class ConsumptionTypeRepo extends CrudImpl<ConsumptionType> {

  // --- Singleton --- //

  private static ConsumptionTypeRepo instance = null;

  private ConsumptionTypeRepo() {
    this.type = new ConsumptionType();
  }

  public static ConsumptionTypeRepo getInstance() {
    if (instance == null) {
      instance = new ConsumptionTypeRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(ConsumptionType someEntity) {
    return someEntity.getId();
  }
}
