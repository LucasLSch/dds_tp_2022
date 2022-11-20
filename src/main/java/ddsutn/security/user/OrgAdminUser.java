package ddsutn.security.user;

import ddsutn.domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;

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

}
