package repositories;

import domain.journey.transport.Line;

public class LineRepo extends CrudImpl<Line> {

  // --- Singleton --- //

  private static LineRepo instance = null;

  private LineRepo() {
    this.type = new Line();
  }

  public static LineRepo getInstance() {
    if (instance == null) {
      instance = new LineRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
