package security.user;

import java.io.IOException;
import security.passwordvalidator.PasswordValidator;

public abstract class User {

  private String username;
  private String password;

  public Boolean successfulLogin(String someUsername, String somePassword) {
    return someUsername.equals(this.username) && somePassword.equals(this.password);
  }

  protected User(String someUsername, String somePassword) throws IOException {
    this.username = someUsername;
    new PasswordValidator().validatePassowrd(somePassword);
    this.password = somePassword;
  }

  public String getUsername() {
    return this.username;
  }
}
