package ddsutn.security.user;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.IOException;

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

}
