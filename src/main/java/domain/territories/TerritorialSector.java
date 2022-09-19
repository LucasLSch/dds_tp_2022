package domain.territories;

import domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

  @OneToMany(mappedBy = "territorialSector")
  private Set<Organization> organizations = Collections.emptySet();

  public TerritorialSector(TerritorialSectorType type, Set<TerritorialSectorAgent> agents, Set<Organization> organizations) {
    this.type = type;
    this.agents = agents;
    this.organizations = organizations;
  }
}
