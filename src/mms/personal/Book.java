package mms.personal;

/**
 * A subclass in Personal
 * Represent a book
 */
public class Book extends Personal {
    /**
     * The title of this book.
     */
    private String title;

    /**
     * (boolean) Fiction or non-fiction.
     */
    private boolean isFiction;

    /**
     * Class Book constructor specifying owner, title and fiction or non-fiction.
     *
     * @param owner  The specified name of owner
     * @param title  The specified name of the book
     * @param isFiction  True if a book is fiction, False if a book is non-fiction
     * Provides properties width, height, length; with defaults 20., 20., 5. (cm)
     * @throws IllegalArgumentException if title is null or empty string
     */
    public Book(String owner, String title, boolean isFiction) throws IllegalArgumentException {
        super(owner);
        this.title = title;
        this.isFiction = isFiction;
        this.setDimensions(20., 20., 5.);

        if (this.title == null || this.title == "") {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getting this book's title.
     *
     * @return the string of the book's title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representing this book with title and fiction or non-fiction
     */
    @Override
    public String toString() {
        String genre;
        if (this.isFiction == true) {
            genre = "Fiction";
        } else {
            genre = "Non-Fiction";
        }
        String toPrint = "Book (" + this.getOwner() + ")" + " Title: " + this.getTitle() + " ("
                + genre + ")";
        return toPrint;
    }

}