package ddsutn.dtos;

import ddsutn.domain.territories.TerritorialSector;
import ddsutn.domain.territories.TerritorialSectorType;
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

  public TerritorialSector toTerritorialSector() {
    return new TerritorialSector(this.name, TerritorialSectorType.valueOf(this.type));
  }
}
