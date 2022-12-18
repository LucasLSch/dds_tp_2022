package ddsutn.domain.territories;

import ddsutn.domain.measurements.CarbonFootprint;
import ddsutn.domain.measurements.unit.Unit;
import ddsutn.domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "territorial_sector")
public class TerritorialSector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  @Enumerated(value = EnumType.STRING)
  private TerritorialSectorType type;

  @OneToMany()
  @JoinColumn(name = "organization_id")
  private Set<Organization> organizations = Collections.emptySet();

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "territorial_sector_id")
  private Set<CarbonFootprint> carbonFootprints = Collections.emptySet();


  public TerritorialSector(String name, TerritorialSectorType type, Set<Organization> organizations) {
    this.name = name;
    this.type = type;
    this.organizations = organizations;
  }

  public TerritorialSector(String name, TerritorialSectorType type) {
    this.name = name;
    this.type = type;
  }


  public CarbonFootprint getCarbonFootprint(Set<Unit> units) {
    CarbonFootprint finalCf = CarbonFootprint.sum(units, this.getOrganizations()
        .stream()
        .map(org -> org.getTotalCarbonFootprint(units)).toArray(CarbonFootprint[]::new));

    this.registerCarbonFootprint(finalCf);
    return finalCf;
  }

  public CarbonFootprint getCarbonFootprint() {
    return getCarbonFootprint(CarbonFootprint.getDefaultUnit());
  }

  private void registerCarbonFootprint(CarbonFootprint someCarbonFootprint) {
    someCarbonFootprint.setDate(LocalDate.now());
    this.carbonFootprints.add(someCarbonFootprint);
  }

  public void addOrganization(Organization someOrganization) {
    this.organizations.add(someOrganization);
  }

}
