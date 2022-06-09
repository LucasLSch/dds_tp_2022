package repositories;

import domain.organization.Member;

public class MemberRepo extends CrudImpl<Member> {

  // --- Singleton --- //

  private static MemberRepo instance = null;

  private MemberRepo(){
  }

  public static MemberRepo getInstance() {
    if (instance == null) {
      instance = new MemberRepo();
      instance.initSavedEntities();
    }
    return instance;
  }
}
