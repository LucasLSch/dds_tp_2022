package ddsutn.dtos.organization;

import ddsutn.domain.organization.Sector;
import ddsutn.domain.organization.workApplication.WorkApplication;
import ddsutn.domain.organization.workApplication.WorkApplicationState;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class SectorForView {
  public Long id;
  public String name;
  public Integer membersAmount;
  public Set<WorkApplicationForView> workApplications;

  public SectorForView(Sector someSector) {
    this.setId(someSector.getId());
    this.setName(someSector.getName());
    this.setMembersAmount(someSector.membersAmount());
    this.workApplications = someSector.getWorkApplications()
            .stream()
            .filter(wa -> wa.stateIs(WorkApplicationState.PENDING))
            .map(WorkApplicationForView::new).collect(Collectors.toSet());
  }
}