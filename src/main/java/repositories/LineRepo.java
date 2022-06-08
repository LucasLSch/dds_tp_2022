package repositories;

import domain.journey.transport.Line;

public class LineRepo extends CRUDImpl<Line> {

  // --- Singleton --- //

  private static LineRepo instance = null;

  private LineRepo(){};

  public static LineRepo getInstance() {
    if(instance == null) {
      instance = new LineRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
