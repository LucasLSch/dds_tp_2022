package repositories;

import security.user.User;

public class UserRepo extends CRUDImpl<User> {

  // --- Singleton --- //

  private static UserRepo instance = null;

  private UserRepo(){};

  public static UserRepo getInstance() {
    if(instance == null) {
      instance = new UserRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
