package domain.exceptions;

public class NotShareableJourneyException extends RuntimeException {

    private static String errorMessage = "Journey not shareable";

    public NotShareableJourneyException() { super(errorMessage); }
}
