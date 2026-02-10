package ginger;

/**
 * Custom exception class to handle all exceptions thrown by
 * Ginger application.
 */
public class GingerException extends Exception {
    public GingerException(String message) {
        super(message);
    }
}
