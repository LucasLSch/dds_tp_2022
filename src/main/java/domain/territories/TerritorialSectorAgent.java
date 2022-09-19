package domain.territories;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
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
