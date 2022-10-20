package ddsutn.security.user;

import ddsutn.domain.organization.Member;
import java.io.IOException;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue(value = "STANDARD_USER")
public class StandardUser extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  public StandardUser(String username, String password, Member member) throws IOException {
    super(username, password);
    this.member = member;
  }

}
