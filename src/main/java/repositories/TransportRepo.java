package repositories;

import domain.journey.transport.Transport;

public class TransportRepo extends CrudImpl<Transport> {

  // --- Singleton --- //

  private static TransportRepo instance = null;

  private TransportRepo() {
    this.type = new Transport(){
      @Override
      public Boolean isShareable() {
        return null;
      }
    };
  }

  public static TransportRepo getInstance() {
    if (instance == null) {
      instance = new TransportRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
