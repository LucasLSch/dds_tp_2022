package ddsutn.security.passwordvalidator;

public class ValidateSpecialChar implements PasswordCriteria {

  private String errorDescription = "The password must have a special character";

  @Override
  public void validatePassword(String somePassword) {
    if (!passwordHasSpecialChar(somePassword)) {
      throw new PasswordException(this.errorDescription);
    }
  }

  private Boolean passwordHasSpecialChar(String somePassword) {
    String regexHasSpecialChar = ".*[!,@,#,$,%,^,&,*,(,)].*";
    return somePassword.matches(regexHasSpecialChar);
  }

  @Override
  public String getDescription() {
    return this.errorDescription;
  }
}
