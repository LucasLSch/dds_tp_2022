package ddsutn.security.user;

import ddsutn.security.passwordvalidator.PasswordValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
@Getter
@Setter
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

  protected User(String someUsername, String somePassword) throws IOException {
    this.username = someUsername;
    new PasswordValidator().validatePassowrd(somePassword);
    this.password = somePassword;
  }

  public Boolean successfulLogin(String someUsername, String somePassword) {
    return someUsername.equals(this.username) && somePassword.equals(this.password);
  }

  public String getUsername() {
    return this.username;
  }
}
