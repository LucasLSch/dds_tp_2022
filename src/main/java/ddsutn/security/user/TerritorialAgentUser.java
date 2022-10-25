package ddsutn.security.user;

import ddsutn.domain.territories.TerritorialSectorAgent;
import java.io.IOException;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue(value = "AGENT_USER")
public class TerritorialAgentUser extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "territorial_sector_agent_id", referencedColumnName = "id")
  private TerritorialSectorAgent territorialSectorAgent;

  public TerritorialAgentUser(String username,
                              String password,
                              TerritorialSectorAgent agent) throws IOException {
    super(username, password);
    this.territorialSectorAgent = agent;
  }

}