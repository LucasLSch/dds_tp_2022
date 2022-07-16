package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
import domain.location.Location;
import domain.measurments.ActivityData;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
public class Organization implements Visited {

  private Set<Sector> sectorList;
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType orgType;
  private List<ActivityData> activityDataList = new ArrayList<>();

  public Organization(String socObj, Location locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.sectorList = new HashSet<>();
  }

  @Override
  public Double acceptVisitor(VisitorCF aVisitor) {
    return aVisitor.calculateHCOrg(this);
  }
  public Set<Sector> getDataActivities(){
    return sectorList;//just for coding
  }
  public Double AverageHcByMember(VisitorCF unVisitor){
    return sectorList.stream().mapToDouble(sector -> sector.acceptVisitor(unVisitor)/sector.cantMember()).sum() ;
  }
  public void registerSector(Sector someSector) {
    try {
      this.validateSector(someSector);
    } catch (InvalidSectorForOrgException exception) {
      System.out.println("WARN: Sector does not belong to this Organization");
      return;
    }
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
    return this.sectorList
        .stream()
        .flatMap(sector -> sector.getMembers().stream())
        .collect(Collectors.toSet());
  }

  public void addActivityData(ActivityData someAD) {
    this.activityDataList.add(someAD);
  }

}