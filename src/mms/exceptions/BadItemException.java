package mms.exceptions;

/**
 * A subclass in custom exception PackingException.
 * Checking invalid item's class while packing.
 */
public class BadItemException extends PackingException {
    /**
     * BadItemException constructor
     */
    public BadItemException() {
        super();
    }

    /**
     * BadItemException constructor specifying detailed message to print out
     *
     * @param message The message to show
     */
    public BadItemException(String message) {
        super(message);
    }
}