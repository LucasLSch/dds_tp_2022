package domain.exceptions;

public class NotSharedOrganizationException extends RuntimeException{

    private static final String errorMessage = "Members do not share organization for the specified journey";

    public NotSharedOrganizationException() { super(errorMessage); }
}
