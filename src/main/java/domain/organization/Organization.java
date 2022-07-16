package domain.organization;

import domain.exceptions.InvalidSectorForOrgException;
import domain.journey.Journey;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.CarbonFootprint;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Organization {

  private Set<Sector> sectorList;
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType orgType;
  private List<ActivityData> activitiesData = new ArrayList<>();

  public Organization(String socObj, Location locat, String clasific, OrgType orgType) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.orgType = orgType;
    this.sectorList = new HashSet<>();
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
    this.getActivitiesData().add(someAD);
  }

  public void notifyAllMembers(String someMessage) {
      this.getMembers().forEach(member -> member.notify(someMessage));
  }


  public CarbonFootprint getTotalCarbonFootprint(String someUnit) {
    List<CarbonFootprint> cfList= new ArrayList<CarbonFootprint>();

    cfList.addAll(this.getActivitiesDataCF(someUnit));
    cfList.addAll(this.getMemberCF(someUnit));

    return CarbonFootprint.sum(someUnit, cfList.toArray(new CarbonFootprint[0]));
  }

  private List<CarbonFootprint> getMemberCF(String someUnit) {

    Set<Journey> journeys = this.getSectorList()
        .stream()
        .flatMap(sector -> sector.getMembersJourneys().stream())
        .collect(Collectors.toSet());

    return journeys
        .stream()
        .map(journey -> journey.getCarbonFootprint(someUnit))
        .collect(Collectors.toList());
  }

  public List<CarbonFootprint> getActivitiesDataCF(String someUnit) {
    return this.activitiesData
        .stream()
        .map(ad -> ad.getCarbonFootprint(someUnit))
        .collect(Collectors.toList());
  }

}