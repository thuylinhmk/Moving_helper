package mms.personal;

import mms.utility.Size;

/**
 * A subclass in Personal
 * Representing a cloth item
 */
public class Clothes extends Personal {
    /**
     * The size of this cloth
     */
    private Size size;
    /**
     * The clothType of this cloth
     */
    private ClotheType clotheType;

    /**
     * Class Clothes constructor specifying name of owner, size and cloth type
     *
     * @param owner  The name of owner
     * @param size  The size of this cloth
     * @param type  The type of this cloth
     */
    public Clothes(String owner, Size size, ClotheType type) {
        super(owner);
        this.size = size;
        this.clotheType = type;

        //setting the cloths measurement based on its size
        switch (this.getSize()) {
            case SMALL:
                setDimensions(40, 65, 10);
                break;
            case MEDIUM:
                setDimensions(50, 70, 10);
                break;
            case LARGE:
                setDimensions(55, 75, 10);
        }

    }

    /**
     * Getting the type of this cloth
     *
     * @return the type of this cloth
     */
    public ClotheType getType() {
        return this.clotheType;
    }

    /**
     * Getting the size of this cloth
     *
     * @return the size of this cloth
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representing this cloth with owner, size and type
     */
    @Override
    public String toString() {
        String toPrint = "Clothes (" + this.getOwner() + ") (" + this.getSize() + ", "
                + this.getType() + ")";
        return toPrint;
    }
}
