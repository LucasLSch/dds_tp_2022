package security.passwordValidator;

public interface PasswordCriteria {

    String getDescription();

    void validatePassword(String aPassword);
}
