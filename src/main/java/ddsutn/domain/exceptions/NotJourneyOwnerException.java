package ddsutn.domain.exceptions;

public class NotJourneyOwnerException extends RuntimeException {

  private static String errorMessage = "The journey is not yours, so you can not share it";

  public NotJourneyOwnerException() {
    super(errorMessage);
  }

}
