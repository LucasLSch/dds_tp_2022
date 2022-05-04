package security.passwordValidator;

public class ValidateNumber implements PasswordCriteria {

    private String errorDescription = "The password must have a number";

    @Override
    public void validatePassword(String aPassword) {
        if(!passwordHasNumber(aPassword)) throw new PasswordException(this.errorDescription);
    }

    private Boolean passwordHasNumber(String aPassword) {
        return aPassword.matches(".*[0,1,2,3,4,5,6,7,8,9].*");
    }

    @Override
    public String getDescription() {
        return this.errorDescription;
    }
}
