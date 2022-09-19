package security.user;

import domain.organization.Member;
import domain.territories.TerritorialSectorAgent;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "AGENT_USER")
public class TerritorialAgentUser extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "territorial_sector_agent_id", referencedColumnName = "id")
  private TerritorialSectorAgent territorialSectorAgent;

  public TerritorialAgentUser(String username, String password, TerritorialSectorAgent agent) throws IOException {
    super(username, password);
    this.territorialSectorAgent = agent;
  }

}
