package domain.exceptions;

public class InvalidStopForLineException extends RuntimeException {

  private static String errorMessage = "The stop does not belong to the line";

  public InvalidStopForLineException() {
    super(errorMessage);
  }

}
