package domain.exceptions;

public class IncompleteLineException extends RuntimeException {

  private static String errorMessage = "The line must have at least 2 stops";

  public IncompleteLineException() {
    super(errorMessage);
  }

}
