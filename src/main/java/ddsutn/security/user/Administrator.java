package ddsutn.security.user;

import java.io.IOException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "ADMINISTRATOR_USER")
public class Administrator extends User {

  public void configureEF() {
    //TODO
  }

  public Administrator(String someUsername, String somePassword) throws IOException {
    super(someUsername, somePassword);
  }

}
