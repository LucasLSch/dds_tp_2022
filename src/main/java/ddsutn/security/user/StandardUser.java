package ddsutn.security.user;

import ddsutn.domain.organization.Member;
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
@DiscriminatorValue(value = "STANDARD_USER")
public class StandardUser extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  public StandardUser(String username, String password, Member member) throws IOException {
    super(username, password);
    this.member = member;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("STANDARD_USER");
    return new ArrayList<>(Collections.singleton(simpleGrantedAuthority));
  }
}
