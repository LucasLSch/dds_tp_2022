package ddsutn.dtos.organization;

import ddsutn.domain.organization.Sector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorForView {
  public Long id;
  public String name;
  public Integer membersAmount;

  public SectorForView(Sector someSector) {
    this.setId(someSector.getId());
    this.setName(someSector.getName());
    this.setMembersAmount(someSector.membersAmount());
  }
}