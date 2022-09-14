package domain.organization;

import domain.contact.Contact;
import domain.exceptions.NotJourneyOwnerException;
import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.UnitExpression;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public void applyToSector(Sector someSector) {
    someSector.registerMember(this);
  }

  public void addSector(Sector someSector) {
    if(someSector.hasMember(this)) {
      this.sectors.add(someSector);
    }
  }

  public Set<Organization> getOrganizations() {
    return this.sectors
        .stream()
        .map(Sector::getOrganization)
        .collect(Collectors.toSet());
  }

  public Boolean worksIn(Sector someSector) {
    return this.sectors.contains(someSector);
  }

  public Boolean worksIn(Organization someOrganization) {
    return this.getOrganizations().contains(someOrganization);
  }

  public Boolean hasJourney(Journey someJourney) {
    return this.journeys.contains(someJourney);
  }

  public void addJourney(Journey someJourney) {
    if (!this.hasJourney(someJourney)) {
      this.journeys.add(someJourney);
      someJourney.addMember(this);
    }
  }

  public void shareJourneyWith(Journey someJourney, Member someMember) {
    try {
      this.validateJourneyPossession(someJourney);
    } catch (NotJourneyOwnerException exception) {
      System.out.println("WARN: You can not a journey you do not own!");
      return;
    }
    someJourney.beSharedWith(someMember);
  }

  public void validateJourneyPossession(Journey someJourney) {
    if (!this.hasJourney(someJourney)) {
      throw new NotJourneyOwnerException();
    }
  }

  public CarbonFootprint getPersonalCF(UnitExpression someUnitExpression) {
    return CarbonFootprint.sum(someUnitExpression, this.journeys
        .stream()
        .map(journey -> journey
            .getCarbonFootprint(someUnitExpression))
            .toArray(CarbonFootprint[]::new));
  }

  public void notify(String someMessage) {
    this.contacts.forEach(contact -> contact.notify(someMessage));
  }

}