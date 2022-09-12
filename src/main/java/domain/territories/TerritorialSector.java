package domain.territories;

import domain.organization.Organization;
import java.util.Collections;
import java.util.Set;

public class TerritorialSector {

  private TerritorialSectorType type;
  private Set<TerritorialSectorAgent> agents = Collections.emptySet();
  private Set<Organization> organizations = Collections.emptySet();

}
