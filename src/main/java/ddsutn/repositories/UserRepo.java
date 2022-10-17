package ddsutn.repositories;

import ddsutn.security.user.User;

public class UserRepo extends CrudImpl<User> {

  // --- Singleton --- //

  private static UserRepo instance = null;

  private UserRepo() {
    try {
      this.type = (Class<User>) Class.forName("ddsutn.security.user.User");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
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
