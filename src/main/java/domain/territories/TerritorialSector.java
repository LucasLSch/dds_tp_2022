package domain.territories;

import domain.measurements.CarbonFootprint;
import domain.measurements.unit.Unit;
import domain.organization.Organization;
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

  @Column(name = "type")
  @Enumerated(value = EnumType.STRING)
  private TerritorialSectorType type;

  @OneToMany(cascade = {
          CascadeType.REFRESH,
          CascadeType.PERSIST
  }, mappedBy = "territorialSector")
  private Set<TerritorialSectorAgent> agents = Collections.emptySet();

  @OneToMany()
  @JoinColumn(name = "organization_id")
  private Set<Organization> organizations = Collections.emptySet();

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "territorial_sector_id")
  private Set<CarbonFootprint> carbonFootprints;


  public TerritorialSector(TerritorialSectorType type, Set<TerritorialSectorAgent> agents, Set<Organization> organizations) {
    this.type = type;
    this.agents = agents;
    this.organizations = organizations;
  }

  public CarbonFootprint getCF(Set<Unit> units) {
    CarbonFootprint finalCf = CarbonFootprint.sum(units, this.getOrganizations()
            .stream()
            .map(org -> org.getTotalCarbonFootprint(units)).toArray(CarbonFootprint[]::new));

    this.registerCarbonFootprint(finalCf);
    return finalCf;
  }

  private void registerCarbonFootprint(CarbonFootprint someCarbonFootprint) {
    someCarbonFootprint.setDate(LocalDate.now());
    this.carbonFootprints.add(someCarbonFootprint);
  }
}
