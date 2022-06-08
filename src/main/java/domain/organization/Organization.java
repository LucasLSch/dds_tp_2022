package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
import domain.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Organization {

  private List<Sector> sectorList;
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType orgType;

  public Organization(String socObj, Location locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.sectorList = new ArrayList<>();
  }

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

  public List<Member> getMembers() {
    List<Member> totalMembers = new ArrayList<>();
    for(Sector sector : this.sectorList) {
      totalMembers.addAll(sector.getMemberList());
    }
    return totalMembers;
  }

}