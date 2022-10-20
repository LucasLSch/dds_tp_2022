package ddsutn.domain.exceptions;

public class NotSharedOrganizationException extends RuntimeException {

  private static final String errorMessage = "Members do not share organization";

  public NotSharedOrganizationException() {
    super(errorMessage);
  }
}
