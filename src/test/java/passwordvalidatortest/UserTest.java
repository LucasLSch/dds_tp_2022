package passwordvalidatortest;

import org.junit.jupiter.api.Test;
import security.user.Administrator;
import security.user.StandardUser;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

  @Test
  public void seCreaAdminCorrectamente() {
    String someUsername = "usuario";
    String somePassword = "1Contra$eña";

    AtomicReference<Administrator> newAdmin = new AtomicReference<>();

    assertDoesNotThrow( () -> newAdmin.set(new Administrator(someUsername, somePassword)));
    assertEquals(newAdmin.get().getUsername(), someUsername);
  }

  @Test
  public void seCreaUsuarioCorrectamente() {
    String someUsername = "usuario";
    String somePassword = "1Contra$eña";

    AtomicReference<StandardUser> newStandardUser = new AtomicReference<>();

    assertDoesNotThrow( () -> newStandardUser.set(new StandardUser(someUsername, somePassword)));
    assertEquals(newStandardUser.get().getUsername(), someUsername);
  }

}
