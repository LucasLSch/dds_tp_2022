package ddsutn.security.passwordvalidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ValidateCommonPassword implements PasswordCriteria {

  private final Path commonPasswordsFilePath = Paths.get("src/main/resources/commonPasswords.txt");
  private final Charset charset = StandardCharsets.UTF_8;
  private final List<String> lineasWorstPwd;
  private final String errorDescription = "La contraseña es muy común";

  public ValidateCommonPassword() throws IOException {
    this.lineasWorstPwd = new ArrayList<>();
    this.separateLines();
  }

  @Override
  public void validatePassword(String somePassword) {
    if (passwordIsCommon(somePassword)) {
      throw new PasswordException(this.errorDescription);
    }
  }

  private Boolean passwordIsCommon(String somePassword) {
    return this.lineasWorstPwd.contains(somePassword);
  }

  private void separateLines() throws IOException {
    BufferedReader bufReader = Files.newBufferedReader(this.commonPasswordsFilePath, this.charset);

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
