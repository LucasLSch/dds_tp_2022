package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
import domain.location.Location;
import domain.measurements.ActivityData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Organization implements Visited {

  private Set<Sector> sectorList;
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType orgType;
  private List<ActivityData> dataActivities = new ArrayList<>();

  public Organization(String socObj, Location locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.sectorList = new HashSet<>();
  }

  @Override
  public Double acceptVisitor(VisitorCF aVisitor) {
    return aVisitor.calculateCFOrg(this);
  }

  public Double averageCFByMember(VisitorCF someVisitor){
    return sectorList.stream().mapToDouble(sector -> sector.acceptVisitor(someVisitor)/sector.cantMember()).sum() ;
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
    this.getDataActivities().add(someAD);
  }

  public void notifyAllMembers(String someMessage) {
      this.getMembers().forEach(member -> member.notify(someMessage));
  }
}