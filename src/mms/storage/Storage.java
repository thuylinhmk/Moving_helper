package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Representing a storage.
 */
public abstract class Storage extends Object {
    /**
     * Storage width
     */
    private double width;

    /**
     * Storage height
     */
    private double height;

    /**
     * Storage length
     */
    private double length;

    /**
     * Size of the storage
     */
    private Size size;

    /**
     * List of items packed in the storage.
     */
    private List<Packable> storageItems;

    /**
     * Class Storage constructor specifying width, height, length.
     *
     * @param width  The width of the storage in cm
     * @param height  The height of the storage in cm
     * @param length  The length of the storage in cm
     * Provides property size; with default Size.MEDIUM
     * @throws IllegalArgumentException if width or height or length < 0
     */
    public Storage(double width,
                   double height,
                   double length) throws IllegalArgumentException {
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = Size.MEDIUM;
        this.storageItems = new ArrayList<Packable>();

        if (this.width <= 0 || this.height <= 0 || this.length <= 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Class Storage constructor specifying width, height, length, size.
     *
     * @param width  The width of the storage in cm
     * @param height  The height of the storage in cm
     * @param length  The length of the storage in cm
     * @param size  The size of the storage
     * @throws IllegalArgumentException if width or height or length < 0
     */
    public Storage(double width,
                   double height,
                   double length,
                   Size size) throws IllegalArgumentException {
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = size;
        this.storageItems = new ArrayList<Packable>();

        if (this.width <= 0 || this.height <= 0 || this.length <= 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getting the width of this storage.
     *
     * @return the width of this storage
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getting the height of this storage.
     *
     * @return the height of this storage
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getting the length of this storage.
     *
     * @return the length of this storage
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Getting the items packed in this storage. Creating a list like
     * the storage's internal list of items.
     * Modify this list not affect the storage's internal list of items.
     *
     * @return a new list with items the same as the storage's internal list of items
     */
    public List<Packable> getElements() {
        List<Packable> copyStorageItems = List.copyOf(this.storageItems);
        return copyStorageItems;
    }

    /**
     * Getting a list containing all items the same class as specified object.
     *
     * @param reference the specified object
     * @return a list containing all the item in this storage having the same class
     * as the specified object
     */
    public List<Packable> getElementsOfType(Packable reference) {
        List<Packable> referenceList = new ArrayList<Packable>();
        for (Packable item : this.getElements()) {
            if (item.getClass().getSimpleName() == reference.getClass().getSimpleName()) {
                referenceList.add(item);
            }
        }
        return referenceList;
    }

    /**
     * Getting the size of this storage.
     *
     * @return the size of this storage
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Getting the capacity of this storage. Capacity is based on
     * the Size and multiplier set by the particular storage class.
     *
     * @return the capacity of this storage
     */
    public int getCapacity() {
        // variable capacity
        int capacity = 0;
        // calculate capacity based on size
        switch (this.getSize()) {
            case SMALL:
                capacity = 3 * this.getMultiplier();
                break;
            case MEDIUM:
                capacity = 5 * this.getMultiplier();
                break;
            case LARGE:
                capacity = 10 * this.getMultiplier();
                break;
        }
        return capacity;
    }

    /**
     * Getting multiplier.
     *
     * @return int multiplier of this storage
     */
    protected abstract int getMultiplier();

    /**
     * Packing the specified item in the storage.
     *
     * @param item  The item to be packed
     * @throws StorageFullException if this storage is in full capacity or adding the item
     * makes any 2 of the sum of measurement of all items greater than the measurement of this
     * storage
     */
    public void pack(Packable item) throws PackingException {
        //testing variables
        double testingWidth = item.getWidth();
        double testingHeight = item.getHeight();
        double testingLength = item.getLength();

        // calculate the current measurement of previous pack items
        for (Packable packed : this.getElements()) {
            testingWidth += packed.getWidth();
            testingHeight += packed.getHeight();
            testingLength += packed.getLength();
        }

        // create list of boolean for checking condition of item's measurements
        List<Boolean> conditionCheck = new ArrayList<Boolean>();
        if (testingHeight > this.getHeight()) {
            conditionCheck.add(true);
        }
        if (testingWidth > this.getWidth()) {
            conditionCheck.add(true);
        }
        if (testingLength > this.getLength()) {
            conditionCheck.add(true);
        }

        //check packing condition
        if (this.getOccupiedCapacity() < this.getCapacity()) {
            if (conditionCheck.size() < 2) {
                this.storageItems.add(item);
            } else {
                throw new StorageFullException();
            }
        } else {
            throw new StorageFullException();
        }
    }

    /**
     * Unpack item. Unpacked in a First In, First Out (FIFO) format.
     *
     * @return the unpacked item
     */
    public Packable unpack() {
        Packable removedItem = null;
        if (this.getElements().size() != 0) {
            removedItem = this.storageItems.remove(0);
        }
        return removedItem;
    }

    /**
     * Getting occupied capacity of this storage. The number of items packed in this storage.
     *
     * @return the int number of items packed in this storage
     */
    public int getOccupiedCapacity() {
        int numberOfItems = this.getElements().size();
        return numberOfItems;
    }

    /**
     * The human-readable string representing this storage.
     *
     * @return The string representing this storage with its measurement.
     */
    public String toString() {
        String measurement = String.format("%.2f", this.getWidth()) + ", "
                            + String.format("%.2f", this.getHeight())
                            + ", " + String.format("%.2f", this.getLength());
        String toPrint = this.getClass().getSimpleName() + " (" + measurement + ") "
                + this.getSize();
        return toPrint;
    }

    /**
     * The human-readable string representing this storage with specified number of
     * "tab" characters.
     *
     * @param level  The number of "tab" characters
     * @return The human-readable string representing this storage with specified number of
     * "tab" characters.
     * @throws IllegalArgumentException if level < 0
     */
    public String toString(int level) throws IllegalArgumentException {
        // check level condition
        if (level < 0) {
            throw new IllegalArgumentException();
        }

        //String variable to return and String tabs
        String tabs = "\t".repeat(level);
        String elementTabs = "\t".repeat(level + 1);
        String toPrint = tabs + this.toString();

        //string for items
        String itemsString = "";

        for (Packable item : this.getElements()) {
            if (item instanceof Storage) {
                itemsString += System.lineSeparator() + ((Storage) item).toString(level + 1);
            } else {
                itemsString += System.lineSeparator() + elementTabs + item.toString();
            }
        }
        toPrint += itemsString;
        return toPrint;
    }

}
