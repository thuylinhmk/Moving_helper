package mms.storage;

import mms.exceptions.PackingException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * A subclass in Storage.
 * Represent a box.
 */
public class Box extends Storage implements Packable {
    /**
     * This is comment about the box.
     */
    private String comment;

    /**
     * Class Box constructor specifying the box measurements.
     *
     * @param width   The width of the box
     * @param height  The height of the box
     * @param length  The length of the box
     * @param comment  The comment of the box
     * @throws IllegalArgumentException if comment is null
     */
    public Box(double width, double height, double length, String comment)
            throws IllegalArgumentException {
        super(width, height, length);
        this.comment = comment;
        if (this.comment == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Class Box constructor specifying the box measurement and size.
     *
     * @param width   The width of the box
     * @param height  The height of the box
     * @param length  The length of the box
     * @param size  The size of the box
     * @param comment  The comment of the box
     * @throws IllegalArgumentException if comment is null
     */
    public Box(double width, double height, double length, Size size, String comment)
            throws IllegalArgumentException {
        super(width, height, length, size);
        this.comment = comment;
        if (this.comment == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getting fragile status of this box.
     *
     * @return true if this box contains laptop or tv; otherwise, false
     */
    public boolean isFragile() {
        if (this.getOccupiedCapacity() == 0) {
            return false;
        } else {
            for (Packable packed : this.getElements()) {
                if (packed instanceof Laptop
                        || (packed instanceof Furniture
                        && ((Furniture) packed).getType() == FurnitureType.TELEVISION)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Getting the comment of this box.
     *
     * @return the comment of this box
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representing this box with measurement and comment
     */
    @Override
    public String toString() {
        String measurement = String.format("%.2f", this.getWidth()) + ", "
                            + String.format("%.2f", this.getHeight())
                            + ", " + String.format("%.2f", this.getLength());
        String toPrint = this.getClass().getSimpleName() + " (" + measurement + ") "
                            + this.getSize() + " - ";
        if (this.getComment() == "") {
            toPrint += "'\\0'";
        } else {
            toPrint += this.comment;
        }

        if (isFragile()) {
            toPrint += " FRAGILE";
        }

        return toPrint;
    }

    /**
     * Getting the multiplier of this box
     *
     * Provides property multiplier; with default 2
     * @return the multiplier of this box
     */
    protected int getMultiplier() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pack(Packable item) throws PackingException {
        super.pack(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Packable unpack() {
        return super.unpack();
    }

}