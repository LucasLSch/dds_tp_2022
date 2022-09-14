package domain.exceptions;

public class NotSharedOrganizationException extends RuntimeException{

    private static String errorMessage = "Members do not share organization for the specified journey";

    public NotSharedOrganizationException() { super(errorMessage); }
}
