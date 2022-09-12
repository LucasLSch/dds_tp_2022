package domain.territories;

import domain.organization.DocType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TerritorialSectorAgent {

  private String name;
  private String surname;
  private DocType docType;
  private String docNumber;
  private TerritorialSector territorialSector;

}
