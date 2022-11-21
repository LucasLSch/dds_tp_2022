package ddsutn.security.user;

import ddsutn.domain.organization.Organization;
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
@DiscriminatorValue(value = "ORG_ADMIN_USER")
public class OrgAdminUser extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "organization_id", referencedColumnName = "id")
  private Organization organization;

  public OrgAdminUser(String username, String password, Organization organization) throws IOException {
    super(username, password);
    this.organization = organization;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ORG_ADMIN_USER");
    return new ArrayList<>(Collections.singleton(simpleGrantedAuthority));
  }

}
