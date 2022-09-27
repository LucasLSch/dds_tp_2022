package repositories;

import domain.organization.Member;
import security.user.User;

public class MemberRepo extends CrudImpl<Member> {

  // --- Singleton --- //

  private static MemberRepo instance = null;

  private MemberRepo() {
    try {
      this.type = (Class<Member>) Class.forName("domain.organization.Member");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static MemberRepo getInstance() {
    if (instance == null) {
      instance = new MemberRepo();
      instance.initEntityManager();
    }
    return instance;
  }

  @Override
  Object getId(Member someEntity) {
    return someEntity.getId();
  }
}
