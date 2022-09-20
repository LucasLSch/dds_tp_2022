package repositories;

import domain.measurements.CarbonFootprint;

public class CarbonFootprintRepo extends CrudImpl<CarbonFootprint> {

  // --- Singleton --- //

  private static CarbonFootprintRepo instance = null;

  private CarbonFootprintRepo() {
    this.type = new CarbonFootprint();
  }

  public static CarbonFootprintRepo getInstance() {
    if (instance == null) {
      instance = new CarbonFootprintRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
