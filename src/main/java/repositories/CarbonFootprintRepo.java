package repositories;

import domain.measurements.CarbonFootprint;
import security.user.User;

public class CarbonFootprintRepo extends CrudImpl<CarbonFootprint> {

  // --- Singleton --- //

  private static CarbonFootprintRepo instance = null;

  private CarbonFootprintRepo() {
    try {
      this.type = (Class<CarbonFootprint>) Class.forName("domain.measurements.CarbonFootprint");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static CarbonFootprintRepo getInstance() {
    if (instance == null) {
      instance = new CarbonFootprintRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(CarbonFootprint someEntity) {
    System.out.println(someEntity.getId());
    return someEntity.getId();
  }
}
