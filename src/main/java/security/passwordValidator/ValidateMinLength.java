package security.passwordValidator;

public class ValidateMinLength implements PasswordCriteria {

    private Integer minChar = 8;
    private String errorDescription = "The password must have " + this.minChar.toString() + " characters";


    @Override
    public void validatePassword(String aPassword) {
        if(!passwordHasMinChar(aPassword)) throw new PasswordException(this.errorDescription);
    }

    private Boolean passwordHasMinChar(String aPassword) {
        return aPassword.length() >=  this.minChar;
    }


    @Override
    public String getDescription() {
        return this.errorDescription;
    }
}
