package ddsutn.domain.territories;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "territorial_sector_agent")
public class TerritorialSectorAgent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "territorial_sector_id")
  private TerritorialSector territorialSector;

  public TerritorialSectorAgent(TerritorialSector territorialSector) {
    this.territorialSector = territorialSector;
  }
}
