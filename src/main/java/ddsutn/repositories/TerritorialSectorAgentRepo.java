package ddsutn.repositories;

import ddsutn.domain.territories.TerritorialSectorAgent;

public class TerritorialSectorAgentRepo extends CrudImpl<TerritorialSectorAgent> {

  // --- Singleton --- //

  private static TerritorialSectorAgentRepo instance = null;

  private TerritorialSectorAgentRepo() {
    try {
      this.type = (Class<TerritorialSectorAgent>) Class.forName("ddsutn.domain.territories.TerritorialSectorAgent");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static TerritorialSectorAgentRepo getInstance() {
    if (instance == null) {
      instance = new TerritorialSectorAgentRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(TerritorialSectorAgent someEntity) {
    return someEntity.getId();
  }
}
