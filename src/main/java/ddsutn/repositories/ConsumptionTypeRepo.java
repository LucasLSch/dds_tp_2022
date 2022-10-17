package ddsutn.repositories;

import ddsutn.domain.measurements.ConsumptionType;

public class ConsumptionTypeRepo extends CrudImpl<ConsumptionType> {

  // --- Singleton --- //

  private static ConsumptionTypeRepo instance = null;

  private ConsumptionTypeRepo() {
    try {
      this.type = (Class<ConsumptionType>) Class.forName("ddsutn.domain.measurements.ConsumptionType");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
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
