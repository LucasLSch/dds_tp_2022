package domain.territories;

import domain.organization.Organization;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "territorial_sector")
public class TerritorialSector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type")
  @Enumerated(value = EnumType.STRING)
  private TerritorialSectorType type;

  @OneToMany
  private Set<TerritorialSectorAgent> agents = Collections.emptySet();

  @OneToMany(mappedBy = "territorialSector")
  private Set<Organization> organizations = Collections.emptySet();

}
