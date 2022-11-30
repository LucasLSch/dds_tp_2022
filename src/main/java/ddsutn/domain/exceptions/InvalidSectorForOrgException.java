package ddsutn.domain.exceptions;

public class InvalidSectorForOrgException extends RuntimeException {

  private static final String errorMessage = " sector does not belong to ";

  public InvalidSectorForOrgException(String someSectorName, String someSocialObjective) {
    super(someSectorName + errorMessage + someSocialObjective);
  }


}
