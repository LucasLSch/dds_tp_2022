package ddsutn.security.user;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.IOException;

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
