package ddsutn.security.passwordvalidator;

public interface PasswordCriteria {

  String getDescription();

  void validatePassword(String somePassword);
}
