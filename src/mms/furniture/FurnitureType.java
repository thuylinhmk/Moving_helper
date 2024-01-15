package mms.furniture;

/**
 *  Furniture types that can be used
 */
public enum FurnitureType {

    /**
     * A bed that you can sleep on.
     */
    BED(1.5, 2, 0.5),

    /**
     * A chair that you can sit on.
     */
    CHAIR(0.5, 1.5, 0.5),

    /**
     * A desk that you can program at.
     */
    DESK(1.2, 2, 1),

    /**
     * A dinning table.
     */
    TABLE(3, 5, 1),

    /**
     * A television that you can relax at.
     */
    TELEVISION(1.3, 0.75, 0.1);

    /**
     * FurnitureType field: width
     */
    public final double width; //in meters

    /**
     * FurnitureType field: height
     */
    public final double height; //in meters

    /**
     * FurnitureType field: length
     */
    public final double length; //in meters

    /**
     * FurnitureType Constructor.
     *
     * @param width   The width of this type
     * @param height  The height of this type
     * @param length  The length of this type
     */
    private FurnitureType(final double width, final double height, final double length) {

        this.width = width;
        this.height = height;
        this.length = length;
    }
}
