package repositories;

import domain.journey.transport.Line;
import security.user.User;

public class LineRepo extends CrudImpl<Line> {

  // --- Singleton --- //

  private static LineRepo instance = null;

  private LineRepo() {
    try {
      this.type = (Class<Line>) Class.forName("domain.journey.transport.Line");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static LineRepo getInstance() {
    if (instance == null) {
      instance = new LineRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Line someEntity) {
    return someEntity.getId();
  }
}
