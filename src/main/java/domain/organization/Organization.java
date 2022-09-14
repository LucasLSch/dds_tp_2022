package domain.organization;

import domain.contact.Contact;
import domain.exceptions.InvalidSectorForOrgException;
import domain.location.Location;
import domain.measurements.ActivityData;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Organization {

  private Set<Sector> sectors;
  private String socialObjective;
  private Location location;
  private String clasification;
  private OrgType type;
  private List<ActivityData> activitiesData;
  private List<Contact> contacts;

  public Organization(String socObj, Location locat, String clasific, OrgType type) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.type = type;
    this.sectors = new HashSet<>();
    this.activitiesData = new ArrayList<>();
  }

  public void registerSector(Sector someSector) {
    try {
      this.validateSector(someSector);
    } catch (InvalidSectorForOrgException exception) {
      System.out.println("WARN: Sector does not belong to this Organization");
      return;
    }
    this.sectors.add(someSector);
  }

  //Posiblemente cambie cuando haya mas validaciones
  private void validateSector(Sector someSector) {
    if (!someSector.belongsTo(this)) {
      throw new InvalidSectorForOrgException(someSector.getName(), this.socialObjective);
    }
  }

  public Boolean sectorIsRegistered(Sector someSector) {
    return this.sectors.contains(someSector);
  }

  public Boolean approvesMember(Member member, Sector sector) {
    return true; //TODO
  }

  public Set<Member> getMembers() {
    return this.sectors
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


  public CarbonFootprint getTotalCarbonFootprint(UnitExpression someUnitExpression) {
    List<CarbonFootprint> cfList = new ArrayList<CarbonFootprint>();

    cfList.addAll(this.getActivitiesDataCF(someUnitExpression));
    cfList.addAll(this.getMemberCF(someUnitExpression));

    return CarbonFootprint.sum(someUnitExpression, cfList.toArray(new CarbonFootprint[0]));
  }

  private List<CarbonFootprint> getMemberCF(UnitExpression someUnitExpression) {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneys().stream())
        .map(journey -> journey.getCarbonFootprint(someUnitExpression))
        .collect(Collectors.toList());
  }

  public List<CarbonFootprint> getActivitiesDataCF(UnitExpression someUnitExpression) {
    return this.activitiesData
        .stream()
        .map(ad -> ad.getCarbonFootprint(someUnitExpression))
        .collect(Collectors.toList());
  }

  public void notify(String someMessage) {
    this.contacts.forEach( contact -> contact.notify(someMessage));
  }

}