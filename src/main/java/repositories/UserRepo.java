package repositories;

import security.user.User;

public class UserRepo extends CrudImpl<User> {

  // --- Singleton --- //

  private static UserRepo instance = null;

  private UserRepo() {
    this.type = new User(){};
  }

  public static UserRepo getInstance() {
    if (instance == null) {
      instance = new UserRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(User someEntity) {
    return someEntity.getId();
  }
}
