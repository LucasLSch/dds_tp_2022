package security.passwordValidator;

public class PasswordException extends RuntimeException {

    public PasswordException(String error) {
        super(error);
    }
}
