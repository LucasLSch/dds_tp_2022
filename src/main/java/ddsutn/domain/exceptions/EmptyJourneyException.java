package ddsutn.domain.exceptions;

public class EmptyJourneyException extends RuntimeException {

  private static String errorMessage = "The journey rerquires at least 1 leg";

  public EmptyJourneyException() {
    super(errorMessage);
  }

}
