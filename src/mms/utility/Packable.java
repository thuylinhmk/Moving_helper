package mms.utility;

/**
 * Items that can be packed into a container
 */
public interface Packable {
    /**
     * Getting the width of this item.
     *
     * @return the width of this item
     */
    public double getWidth(); //cm

    /**
     * Getting the height of this item.
     *
     * @return the height of this item
     */
    public double getHeight(); //cm

    /**
     * Getting the length of this item.
     *
     * @return the length of this item
     */
    public double getLength(); //cm

    /**
     * Getting volume of this item
     * volume = width x height x length
     *
     * @return the volume of this item
     */
    public default double getVolume() {
        double volume = getWidth() * getHeight() * getLength(); //cm3
        return volume;
    }

}