package mms.personal;

import mms.utility.Packable;

/**
 * A Class represents a personal item
 */
public abstract class Personal extends Object implements Packable {
    /**
     * Name of the owner
     */
    private String owner;
    /**
     * Personal item width
     */
    private double width;

    /**
     * Personal item height
     */
    private double height;

    /**
     * Personal item length
     */
    private double length;

    /**
     *  Base weight of a personal item
     */
    private static int baseWeight;

    /**
     * Class Personal constructor specifying owner.
     *
     * @param owner  The name of the owner of this personal item
     * @throws IllegalArgumentException if owner's name is null or empty string
     */
    public Personal(String owner) throws IllegalArgumentException {
        //set variables
        this.owner = owner;
        this.setDimensions(0., 0., 0.);
        this.baseWeight = 250;

        // checking valid owner's name
        if (this.owner == null || this.owner == "") {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Class Personal constructor specifying owner and measurement in cm.
     *
     * @param owner  The name of the owner
     * @param width  The width of this item in cm
     * @param height  The height of this item in cm
     * @param length  The length of this item in cm
     * Provides property baseWeight; with default 250 grams
     * @throws IllegalArgumentException if owner name is null or empty string or any of
     * measurement < 0
     */
    public Personal(String owner,
                    double width,
                    double height,
                    double length) throws IllegalArgumentException {
        this.owner = owner;
        this.setDimensions(width, height, length);
        this.baseWeight = 250;

        // checking valid arguments
        if (this.width < 0 || this.height < 0 || this.length < 0
                || this.owner == null || this.owner == "") {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getting the base weight of this item.
     *
     * @return the baseWeight of this item
     */
    public static int getBaseWeight() {
        return baseWeight;
    }

    /**
     * Getting the width of this item.
     *
     * @return the width of this item
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getting the height of this item.
     *
     * @return the height of this item
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getting the length of this item.
     *
     * @return the length of this item
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Setting the measurement into new measurement in cm.
     *
     * @param width  The new width in cm
     * @param height  The new height in cm
     * @param length  The new length in cm
     */
    protected void setDimensions(double width,
                                 double height,
                                 double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Getting owner name of this item.
     *
     * @return the string of the owner's name
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Getting the human-readable string representing this personal item.
     *
     * @return the string representing this personal item with owner's name
     */
    public String toString() {
        String toPrint = this.getClass().getSimpleName() + " (" + this.getOwner() + ")";
        return toPrint;
    }

}