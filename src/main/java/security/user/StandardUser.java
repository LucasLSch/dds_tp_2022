package security.user;

import java.io.IOException;

public class StandardUser extends User {

  public StandardUser(String someUsername, String somePassword) throws IOException {
    super(someUsername, somePassword);
  }

}
