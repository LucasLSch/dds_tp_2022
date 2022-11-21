package ddsutn.security.user;

import ddsutn.domain.territories.TerritorialSectorAgent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("AGENT_USER");
    return new ArrayList<>(Collections.singleton(simpleGrantedAuthority));
  }

}
