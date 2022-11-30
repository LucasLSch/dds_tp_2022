package ddsutn.domain.exceptions;

public class InvalidUnitException extends RuntimeException {

  private static final String errorMessage = "The unit does not match";

  public InvalidUnitException() {
    super(errorMessage);
  }
}
