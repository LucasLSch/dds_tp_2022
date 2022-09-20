package repositories;

import domain.territories.TerritorialSectorAgent;

public class TerritorialSectorAgentRepo extends CrudImpl<TerritorialSectorAgent> {

  // --- Singleton --- //

  private static TerritorialSectorAgentRepo instance = null;

  private TerritorialSectorAgentRepo() {
    this.type = new TerritorialSectorAgent();
  }

  public static TerritorialSectorAgentRepo getInstance() {
    if (instance == null) {
      instance = new TerritorialSectorAgentRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
