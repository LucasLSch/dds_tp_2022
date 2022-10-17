package ddsutn.repositories;

import ddsutn.domain.journey.transport.Transport;

public class TransportRepo extends CrudImpl<Transport> {

  // --- Singleton --- //

  private static TransportRepo instance = null;

  private TransportRepo() {
    try {
      this.type = (Class<Transport>) Class.forName("ddsutn.domain.journey.transport.Transport");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static TransportRepo getInstance() {
    if (instance == null) {
      instance = new TransportRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Transport someEntity) {
    return someEntity.getId();
  }
}
