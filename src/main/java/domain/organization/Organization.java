package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
import domain.location.Location;
import domain.measurments.ActivityData;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class Organization {

  private List<Sector> sectorList = new ArrayList<>();
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType orgType;
  private List<ActivityData> activityDataList = new ArrayList<>();

  public void registerSector(Sector someSector) {
    this.validateSector(someSector);
    this.sectorList.add(someSector);
  }

  //Posiblemente cambie cuando haya mas validaciones
  private void validateSector(Sector someSector) {
    if (!someSector.belongsTo(this)) {
      throw new InvalidSectorForOrgException(someSector.getSectorName(), this.socialObjective);
    }
  }

  public Boolean sectorIsRegistered(Sector someSector) {
    return this.sectorList.contains(someSector);
  }

  public Boolean approvesMember(Member member, Sector sector) {
    return true; //TODO
  }

  public Set<Member> getMembers() {
    Set<Member> totalMembers = new HashSet<>();
    for (Sector sector : this.sectorList) {
      totalMembers.addAll(sector.getMemberList());
    }
    return totalMembers;
  }

  public void addActivityData(ActivityData someAD) {
    this.activityDataList.add(someAD);
  }

}