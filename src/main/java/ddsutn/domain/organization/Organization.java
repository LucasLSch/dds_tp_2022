package ddsutn.domain.organization;

import ddsutn.domain.contact.Contact;
import ddsutn.domain.location.Location;
import ddsutn.domain.measurements.ActivityData;
import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
  private Set<Sector> sectors;

  @Column(name = "social_objective")
  private String socialObjective;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @Column(name = "clasification")
  private String clasification;

  @Column(name = "type")
  @Enumerated(value = EnumType.STRING)
  private OrgType type;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "organization_id")
  private List<ActivityData> activitiesData;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organization_id")//esto no es una lista es un solo contacto
  private List<Contact> contacts;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "organization_id")
  private Set<CarbonFootprint> carbonFootprints;

  public Organization(String socObj, Location locat, String clasific, OrgType type) {
    this.socialObjective = socObj;
    this.location = locat;
    this.clasification = clasific;
    this.type = type;
    this.sectors = new HashSet<>();
    this.activitiesData = new ArrayList<>();
    this.carbonFootprints = new HashSet<>();
    this.contacts = new ArrayList<>();
  }

  public void createSector(String name) {
    if (this.sectors.stream().noneMatch(sector -> sector.getName().equals(name)))
      this.sectors.add(new Sector(name, this));
  }

  public Boolean sectorIsRegistered(Sector someSector) {
    return this.sectors.contains(someSector);
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


  public CarbonFootprint getTotalCarbonFootprint(Set<Unit> units) {
    List<CarbonFootprint> cfList = new ArrayList<>();

    cfList.addAll(this.getActivitiesDataCF(units));
    cfList.addAll(this.getMemberCF(units));

    CarbonFootprint finalCF = CarbonFootprint.sum(units, cfList.toArray(new CarbonFootprint[0]));
    this.registerCarbonFootprint(finalCF);

    return finalCF;
  }

  public CarbonFootprint getTotalCarbonFootprint() {
    return this.getTotalCarbonFootprint(CarbonFootprint.getDefaultUnit());
  }

  private List<CarbonFootprint> getMemberCF(Set<Unit> units) {
    return this.getMembers()
        .stream()
        .flatMap(member -> member.getJourneys().stream())
        .map(journey -> journey.getCarbonFootprint(units))
        .collect(Collectors.toList());
  }

  public List<CarbonFootprint> getActivitiesDataCF(Set<Unit> units) {
    return this.activitiesData
        .stream()
        .map(ad -> ad.getCarbonFootprint(units))
        .collect(Collectors.toList());
  }

  private void registerCarbonFootprint(CarbonFootprint someCarbonFootprint) {
    someCarbonFootprint.setDate(LocalDate.now());
    this.carbonFootprints.add(someCarbonFootprint);
  }

  public void notify(String someMessage) {
    this.contacts.forEach(contact -> contact.notify(someMessage));
  }

}
