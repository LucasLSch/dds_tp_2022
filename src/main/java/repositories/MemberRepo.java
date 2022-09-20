package repositories;

import domain.organization.Member;

public class MemberRepo extends CrudImpl<Member> {

  // --- Singleton --- //

  private static MemberRepo instance = null;

  private MemberRepo() {
    this.type = new Member();
  }

  public static MemberRepo getInstance() {
    if (instance == null) {
      instance = new MemberRepo();
      instance.initEntityManager();
    }
    return instance;
  }
}
