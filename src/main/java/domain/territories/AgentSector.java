package domain.territories;

import domain.organization.DocType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgentSector {

  private String name;
  private String lastName;
  private DocType docType;
  private String document;
  private TerritorialSector territorialSector;

}
