package security.user;

public abstract class User {

  private String username;
  private String password;

  public Boolean successfulLogin(String someUsername, String somePassword) {
    return someUsername.equals(this.username) && somePassword.equals(this.password);
  }

  protected User(String someUsername, String somePassword) {
    this.username = someUsername;
    this.password = somePassword;
  }

  public String getUsername() {
    return this.username;
  }
}
