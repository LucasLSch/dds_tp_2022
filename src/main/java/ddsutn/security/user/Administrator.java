package ddsutn.security.user;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "ADMINISTRATOR_USER")
public class Administrator extends User {

  public Administrator(String someUsername, String somePassword) throws IOException {
    super(someUsername, somePassword);
  }

  public void configureEF() {
    //TODO
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ADMINISTRATOR_USER");
    return new ArrayList<>(Collections.singleton(simpleGrantedAuthority));
  }
}
