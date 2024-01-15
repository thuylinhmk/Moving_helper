package mms.exceptions;

/**
 * A subclass in custom exception PackingException.
 * Checking invalid packing order
 */
public class PackingOrderException extends PackingException {

    /**
     * PackingOrderException constructor
     */
    public PackingOrderException() {
        super();
    }

    /**
     * PackingOrderException constructor specifying detailed message to print out
     *
     * @param message The message to show
     */
    public PackingOrderException(String message) {
        super(message);
    }

}