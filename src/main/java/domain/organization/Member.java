package domain.organization;

import domain.contact.Contact;
import domain.exceptions.NotJourneyOwnerException;
import domain.journey.Journey;
import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(mappedBy = "members")
  private Set<Sector> sectors;

  @ManyToMany
  @JoinTable(
          name = "member_journey",
          joinColumns = @JoinColumn(name = "member_id"),
          inverseJoinColumns = @JoinColumn(name = "journey_id")
  )
  private List<Journey> journeys;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "doc_type")
  @Enumerated(value = EnumType.STRING)
  private DocType docType;

  @Column(name = "doc_number")
  private String docNumber;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Contact> contacts;

  public Member(String name, String surname, DocType docType, String document) {
    this.name = name;
    this.surname = surname;
    this.docType = docType;
    this.docNumber = document;
    this.sectors = new HashSet<>();
    this.journeys = new ArrayList<>();
    this.contacts = new ArrayList<>();
  }

  public void addContact(Contact someContact) {
    this.contacts.add(someContact);
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

  public void deleteJourney(Journey someJourney) {
    if (this.hasJourney(someJourney)) {
      this.journeys.remove(someJourney);
      someJourney.deleteMember(this);
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

  public CarbonFootprint getPersonalCF(Set<Unit> units) {
    return CarbonFootprint.sum(units, this.journeys
        .stream()
        .map(journey -> journey
            .getCarbonFootprint(units))
            .toArray(CarbonFootprint[]::new));
  }

  public void notify(String someMessage) {
    this.contacts.forEach(contact -> contact.notify(someMessage));
  }

}