package domain.exceptions;

public class InvalidUnitException extends RuntimeException {

  private static String errorMessage = "The unit does not match";

  public InvalidUnitException() {
    super(errorMessage);
  }
}
