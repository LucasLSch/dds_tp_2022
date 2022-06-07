package security.passwordvalidator;

public class PasswordException extends RuntimeException {

  public PasswordException(String error) {
    super(error);
  }
}
