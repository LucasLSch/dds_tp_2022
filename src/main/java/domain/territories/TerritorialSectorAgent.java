package domain.territories;

import domain.organization.DocType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
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

}
