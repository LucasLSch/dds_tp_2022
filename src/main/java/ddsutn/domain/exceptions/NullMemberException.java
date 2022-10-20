package ddsutn.domain.exceptions;

public class NullMemberException extends RuntimeException {
  private static final String message = "object is null";

  public NullMemberException(String type) {
    super(type + " " + message);
  }
}
