package ddsutn.domain.exceptions;

public class IncorrectInputException extends RuntimeException {
  private static final String errorMessage = " input value is not valid";

  public IncorrectInputException(String input) {
    super(input + errorMessage);
  }
}
