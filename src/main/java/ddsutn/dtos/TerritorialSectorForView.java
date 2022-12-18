package ddsutn.dtos;

import ddsutn.domain.territories.TerritorialSector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TerritorialSectorForView {

  public Long id;
  public String name;
  public String type;

  public TerritorialSectorForView(TerritorialSector someTs) {
    this.id = someTs.getId();
    this.name = someTs.getName();
    this.type = someTs.getType().name();
  }
}
