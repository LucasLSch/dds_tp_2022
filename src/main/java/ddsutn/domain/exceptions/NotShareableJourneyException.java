package ddsutn.domain.exceptions;

public class NotShareableJourneyException extends RuntimeException {

    private static String errorMessage = "Specified journey is not shareable";

    public NotShareableJourneyException() { super(errorMessage); }
}
