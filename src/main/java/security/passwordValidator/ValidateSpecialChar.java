package security.passwordValidator;

public class ValidateSpecialChar implements PasswordCriteria {

    private String errorDescription = "The password must have a special character";
    @Override
    public void validatePassword(String aPassword) {
        if(!passwordHasSpecialChar(aPassword)) throw new PasswordException(this.errorDescription);
    }

    private Boolean passwordHasSpecialChar(String aPassword) {
        return aPassword.matches(".*[!,@,#,$,%,^,&,*,(,)].*");
    }

    @Override
    public String getDescription() {
        return this.errorDescription;
    }
}
