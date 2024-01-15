package mms.exceptions;

/**
 * Construct class PackingException extending Exception for invalid packing
 */
public class PackingException extends Exception {

    /**
     * PackingException constructor
     */
    public PackingException() {
        super();
    }

    /**
     * PackingException constructor specifying detailed message to print out
     *
     * @param message The message to show
     */
    public PackingException(String message) {
        super(message);
    }
}