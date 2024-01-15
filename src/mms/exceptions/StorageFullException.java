package mms.exceptions;

/**
 * A subclass in custom exception PackingException.
 * Checking whether the storage is full
 */
public class StorageFullException extends PackingException {

    /**
     * StorageFullException constructor
     */
    public StorageFullException() {
        super();
    }

    /**
     * PStorageFullException constructor specifying detailed message to print out
     *
     * @param message The message to show
     */
    public StorageFullException(String message) {
        super(message);
    }

}