package security.passwordValidator;

public class ValidateCapitalLetter implements PasswordCriteria {

    private String errorDescription = "The password does not have a capital letter";
    @Override
    public void validatePassword(String aPassword) {
        if(!passwordHasCapitalLetter(aPassword)) throw new PasswordException(this.errorDescription);
    }

    private Boolean passwordHasCapitalLetter(String aPassword) {
        return aPassword != aPassword.toLowerCase();
    }

    @Override
    public String getDescription() {
        return this.errorDescription;
    }

}
