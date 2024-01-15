package mms.furniture;

import mms.utility.Packable;

/**
 * Represent furniture
 */
public class Furniture extends Object implements Packable {
    /**
     * Furniture type.
     */
    private FurnitureType type;

    /**
     * Furniture width
     */
    private double  width;

    /**
     * Furniture height
     */
    private double height;

    /**
     * Furniture length
     */
    private double length;

    /**
     * Class Furniture constructor specifying furniture type.
     *
     * @param type  The specified type of this furniture
     */
    public Furniture(FurnitureType type) {
        this.type = type;
    }

    /**
     * Get furniture type.
     *
     * @return the type of this furniture
     */
    public FurnitureType getType() {
        return this.type;
    }

    /** Return a string representing furniture.
     *
     * @return a string representation of the furniture.
     */
    public String toString() {
        String toPrint = "Furniture (" + this.type.toString() + ")";
        return toPrint;
    }

    /**
     * Getting the width of this furniture.
     *
     * @return the width of this furniture in cm
     */
    public double getWidth() {
        this.width = this.type.width * 100; //in cm
        return this.width;
    }

    /**
     * Getting the height of this furniture.
     *
     * @return the height of this furniture in cm
     */
    public double getHeight() {
        this.height = this.type.height * 100; //in cm
        return this.height;
    }

    /**
     * Getting the length of this furniture.
     *
     * @return the length of this furniture in cm
     */
    public double getLength() {
        this.length = this.type.length * 100; //in cm
        return this.length;
    }

}