package domain.organization;

import domain.contact.Contact;
import domain.exceptions.NotSameOrganizationException;
import domain.exceptions.NotShareableJourneyException;
import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

@Getter
public class Member {

  private Set<Sector> sectors;
  private List<Journey> journeys;
  private String name;
  private String surname;
  private DocType docType;
  private String docNumber;
  private List<Contact> contacts;

  public Member(String name, String surname, DocType docType, String document) {
    this.name = name;
    this.surname = surname;
    this.docType = docType;
    this.docNumber = document;
    this.sectors = new HashSet<>();
    this.journeys = new ArrayList<>();
  }

  public void linkSector(Sector someSector) {
    someSector.registerMember(this);
  }

  public void addSector(Sector someSector) {
    this.sectors.add(someSector);
  }

  public void addJourney(Journey someJourney) {
    this.journeys.add(someJourney);
  }

  public void addSharedJourney(Journey someJourney, Member someMember) {
    try {
      this.memberOrgValidation(someMember);
    } catch (NotSameOrganizationException exception) {
      System.out.println("WARN: Members does not work for the same Organization");
      return;
    }
    try {
      someJourney.isJourneyShareable();
    } catch (NotShareableJourneyException exception) {
      System.out.println("WARN: Journey has Legs that are not shareable");
      return;
    }
    this.addJourney(someJourney);
    someMember.addJourney(someJourney);
  }

  public void memberOrgValidation(Member someMember) {
    if (!this.memberSharesOrg(someMember)) {
      throw new NotSameOrganizationException();
    }
  }

  public Boolean memberSharesOrg(Member someMember) {
    return this
        .sectors
        .stream()
        .anyMatch(sector -> someMember.worksIn(sector.getOrganization()));
  }

  public Boolean worksIn(Sector someSector) {
    return this.sectors.contains(someSector);
  }

  public Boolean worksIn(Organization someOrganization) {
    return this.sectors.stream().anyMatch(sector -> sector.belongsTo(someOrganization));
  }

  public void notify(String someMessage) {
    this.contacts.forEach(contact -> contact.notify(someMessage));
  }

  public CarbonFootprint getPersonalCF(UnitExpression someUnitExpression) {
    return CarbonFootprint.sum(someUnitExpression, this.journeys
        .stream()
        .map(journey -> journey
            .getCarbonFootprint(someUnitExpression))
            .toArray(CarbonFootprint[]::new));
  }

}