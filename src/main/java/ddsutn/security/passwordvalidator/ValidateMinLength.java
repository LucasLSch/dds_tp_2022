package ddsutn.security.passwordvalidator;

public class ValidateMinLength implements PasswordCriteria {

  private Integer minChar = 8;
  private String errorDescription = "The password must have " + minChar + " characters";


  @Override
  public void validatePassword(String somePassword) {
    if (!passwordHasMinChar(somePassword)) {
      throw new PasswordException(this.errorDescription);
    }
  }

  private Boolean passwordHasMinChar(String somePassword) {
    return somePassword.length() >= this.minChar;
  }


  @Override
  public String getDescription() {
    return this.errorDescription;
  }
}
