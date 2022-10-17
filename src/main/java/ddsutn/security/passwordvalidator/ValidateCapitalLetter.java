package ddsutn.security.passwordvalidator;

public class ValidateCapitalLetter implements PasswordCriteria {

  private String errorDescription = "The password does not have a capital letter";

  @Override
  public void validatePassword(String somePassword) {
    if (!passwordHasCapitalLetter(somePassword)) {
      throw new PasswordException(this.errorDescription);
    }
  }

  private Boolean passwordHasCapitalLetter(String somePassword) {
    return !somePassword.equals(somePassword.toLowerCase());
  }

  @Override
  public String getDescription() {
    return this.errorDescription;
  }

}
