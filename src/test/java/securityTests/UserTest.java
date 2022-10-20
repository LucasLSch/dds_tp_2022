package securityTests;

import ddsutn.security.user.Administrator;
import ddsutn.security.user.StandardUser;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

  @Test
  public void seCreaAdminCorrectamente() {
    String someUsername = "usuario";
    String somePassword = "1Contra$eña";

    AtomicReference<Administrator> newAdmin = new AtomicReference<>();

    assertDoesNotThrow(() -> newAdmin.set(new Administrator(someUsername, somePassword)));
    assertEquals(newAdmin.get().getUsername(), someUsername);
  }

  @Test
  public void seCreaUsuarioCorrectamente() {
    String someUsername = "usuario";
    String somePassword = "1Contra$eña";

    AtomicReference<StandardUser> newStandardUser = new AtomicReference<>();

    assertDoesNotThrow(() -> newStandardUser.set(new StandardUser(someUsername, somePassword, null)));
    assertEquals(newStandardUser.get().getUsername(), someUsername);
  }

}
