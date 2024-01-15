package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.personal.Personal;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * A subclass in Storage.
 * Represent a Bag.
 */
public class Bag extends Storage implements Packable {
    /**
     * Max weight of a box
     */
    private int maxWeight; //in grams

    /**
     * Class Bag constructor with specified measurements.
     *
     * @param width   The width of the bag
     * @param height  The height of the bag
     * @param length  The length of the bag
     * Provides property maxWeight; with default 1500 (grams)
     */
    public Bag(double width, double height, double length) {
        super(width, height, length);
        this.maxWeight = 1500;
    }

    /**
     * Class Bag constructor with specified measurements and size.
     *
     * @param width   The width of the bag
     * @param height  The height of the bag
     * @param length  The length of the bag
     * @param size  The size of the bag
     * Provides property maxWeight; with default 1500 (grams)
     */
    public Bag(double width, double height, double length, Size size) {
        super(width, height, length, size);
        this.maxWeight = 1500;
    }

    /**
     * Getting the multiplier of this bag
     *
     * Provides property multiplier; with default 1
     * @return the multiplier of this bag
     */
    protected int getMultiplier() {
        return 1;
    }

    /**
     * {@inheritDoc}
     *
     * @param item  The item to be packed
     * @throws BadItemException if item is not personal item
     * @throws StorageFullException if add item cause exceeding max weight of this bag (1500g)
     * or full capacity
     * or adding the item makes any 2 of the sum of measurement of all items
     * greater than the measurement of this storage
     */
    @Override
    public void pack(Packable item) throws PackingException {
        // check if this is personal item
        if (item instanceof Personal) {
            // getting current weight
            int currentWeight = 0;
            for (Packable packed : this.getElements()) {
                currentWeight += ((Personal) item).getBaseWeight();
            }

            if (currentWeight >= this.maxWeight) {
                throw new StorageFullException();
            } else {
                super.pack(item);
            }
        } else {
            throw new BadItemException();
        }
    }
}