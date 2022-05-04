package security.passwordValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidateCommonPassword implements PasswordCriteria {

    private String path10k = "resources/commonPasswords.txt";
    private List<String> lineasWorstPwd;
    private String errorDescription = "The password is not a safe password";

    @Override
    public void validatePassword(String aPassword) {
        if (passwordIsCommon(aPassword)) throw new PasswordException(this.errorDescription);
    }

    private Boolean passwordIsCommon(String aPassword) {
        return this.lineasWorstPwd.contains(aPassword);
    }

    public ValidateCommonPassword() throws IOException {
        this.lineasWorstPwd = new ArrayList<>();
        this.separateLines();
    }

    private void separateLines() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(this.path10k));

        String lineaLeida = bufReader.readLine();
        while (lineaLeida != null) {
            this.lineasWorstPwd.add(lineaLeida);
            lineaLeida = bufReader.readLine();
        }
        bufReader.close();

    }

    @Override
    public String getDescription() {
        return this.errorDescription;
    }

}
