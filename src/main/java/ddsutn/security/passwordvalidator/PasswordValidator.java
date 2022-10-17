package ddsutn.security.passwordvalidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

  private List<PasswordCriteria> criteriaList;

  public void validatePassowrd(String somePassword) {
    this.criteriaList.forEach(criteria -> criteria.validatePassword(somePassword));
  }

  public PasswordValidator() throws IOException {
    this.criteriaList = new ArrayList<>();

    this.criteriaList.add(new ValidateCommonPassword());
    this.criteriaList.add(new ValidateMinLength());
    this.criteriaList.add(new ValidateNumber());
    this.criteriaList.add(new ValidateSpecialChar());
    this.criteriaList.add(new ValidateCapitalLetter());
  }

}
