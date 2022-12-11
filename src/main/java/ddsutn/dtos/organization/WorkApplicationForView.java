package ddsutn.dtos.organization;

import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.workApplication.WorkApplication;

public class WorkApplicationForView {

  public String id;
  public String memberNames;
  public String memberDocs;

  public WorkApplicationForView(WorkApplication wa) {
    this.id = wa.getId();
    this.memberNames = wa.getNames();
    this.memberDocs = wa.getDocs();
  }
}
