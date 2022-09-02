package domain.exceptions;

public class NotSameOrganizationException extends RuntimeException{

    private static String errorMessage = "Members do not share org.";

    public NotSameOrganizationException() { super(errorMessage); }
}
