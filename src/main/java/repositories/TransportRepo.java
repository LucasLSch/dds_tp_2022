package repositories;

import domain.journey.transport.Transport;

public class TransportRepo extends CRUDImpl<Transport> {

  // --- Singleton --- //

  private static TransportRepo instance = null;

  private TransportRepo(){};

  public static TransportRepo getInstance() {
    if(instance == null) {
      instance = new TransportRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
