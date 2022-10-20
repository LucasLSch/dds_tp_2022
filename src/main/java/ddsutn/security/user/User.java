package ddsutn.security.user;

import ddsutn.security.passwordvalidator.PasswordValidator;
import java.io.IOException;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
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
