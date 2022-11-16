package ddsutn.security.passwordvalidator;

public class ValidateNumber implements PasswordCriteria {

  private String errorDescription = "Debe tener algún número";

  @Override
  public void validatePassword(String somePassword) {
    if (!passwordHasNumber(somePassword)) {
      throw new PasswordException(this.errorDescription);
    }
  }

  private Boolean passwordHasNumber(String somePassword) {
    String regexHasNumber = ".*[0,1,2,3,4,5,6,7,8,9].*";
    return somePassword.matches(regexHasNumber);
  }

  @Override
  public String getDescription() {
    return this.errorDescription;
  }
}
