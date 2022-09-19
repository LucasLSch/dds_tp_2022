package security.user;

import domain.organization.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
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
