package rgmek.backend.api.exceptions;

public class BadBodyException extends RuntimeException {
    public BadBodyException(String message) {
        super(message);
    }
}
