package ddsutn.domain.exceptions;

public class IncompleteLineException extends RuntimeException {

  private static final String errorMessage = "The line must have at least 2 stops";

  public IncompleteLineException() {
    super(errorMessage);
  }

}
