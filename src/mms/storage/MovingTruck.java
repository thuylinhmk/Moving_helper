package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.ArrayList;
import java.util.List;


/**
 * A subclass in Storage.
 * Representing a Moving Truck.
 * A moving truck to facilitate moving different items on a road.
 */
public class MovingTruck extends Storage {

    /**
     * Class MovingTruck constructor with specified width, height, length.
     *
     * @param width   The width of the truck
     * @param height  The height of the truck
     * @param length  The length of the truck
     * Default size of the truck is large
     * @throws IllegalArgumentException if length less than 1500 cm
     */
    public MovingTruck(double width, double height, double length)
            throws IllegalArgumentException {
        super(width, height, length, Size.LARGE);
        if (this.getLength() < 1500) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Class MovingTruck constructor with specified width, height, length.
     *
     * @param width   The width of the truck
     * @param height  The height of the truck
     * @param length  The length of the truck
     * @param size  The size of the truck
     * @throws IllegalArgumentException if length less than 1500 cm
     */
    public MovingTruck(double width, double height, double length, Size size)
            throws IllegalArgumentException {
        super(width, height, length, size);
        if (this.getLength() < 1500) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param item  The item to be packed
     * @throws StorageFullException if this storage is in full capacity or adding the item
     * makes any 2 of the sum of
     * measurement of all items greater than the measurement of the storage part of this truck.
     * @throws PackingOrderException if add non-furniture item in the truck while
     * this truck contains furniture
     */
    @Override
    public void pack(Packable item) throws PackingException {
        boolean havingFurniture = false;
        double testingLength = item.getLength();
        double storageLength = this.getLength() - 1500.;

        // checking if there is furniture in the truck, calculate testing length
        for (Packable packed : this.getElements()) {
            if (packed instanceof Furniture) {
                havingFurniture = true;
            }
            testingLength += packed.getLength();
        }

        // try to add item
        if (testingLength > storageLength) {
            throw new StorageFullException();
        } else {
            if (havingFurniture == false) {
                super.pack(item);
            } else {
                if (item instanceof Furniture) {
                    super.pack(item);
                } else {
                    throw new PackingOrderException();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * If this truck contains furniture, unpack all the furniture in a first in, first out format.
     * IF this truck has no more furniture, unpack items in a first in, last out format.
     *
     * @return the unpacked item
     */
    @Override
    public Packable unpack() {
        // helping variables
        Packable removedItem = null; // return
        boolean haveFurniture = false;
        List<Packable> tempoUnpack = List.copyOf(this.getElements());
        int currentNumberOfItem = this.getOccupiedCapacity();
        int firstFurnitureNumber = -1;

        // return null if empty storage
        if (this.getElements().size() == 0) {
            return removedItem;
        }

        //finding the first furniture
        for (int itemNumber = 0; itemNumber <= (currentNumberOfItem - 1); itemNumber++) {
            if (tempoUnpack.get(itemNumber) instanceof Furniture) {
                haveFurniture = true;
                firstFurnitureNumber = itemNumber;
                break;
            }
        }

        // unpack the furniture FIFO if there is, otherwise unpack item FILO
        if (haveFurniture) {
            for (int itemNumber = 0; itemNumber < currentNumberOfItem; itemNumber++) {
                super.unpack();
                if (itemNumber == firstFurnitureNumber) {
                    removedItem = tempoUnpack.get(itemNumber);
                } else {
                    try {
                        super.pack(tempoUnpack.get(itemNumber));
                    } catch (PackingException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (int itemNumber = 0; itemNumber < currentNumberOfItem; itemNumber++) {
                super.unpack();
                if (itemNumber == currentNumberOfItem - 1) {
                    removedItem = tempoUnpack.get(itemNumber);
                } else {
                    try {
                        super.pack(tempoUnpack.get(itemNumber));
                    } catch (PackingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return removedItem;
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representing this moving truck with occupied capacity over its capacity
     */
    public String toString() {
        String toPrint = this.getClass().getSimpleName() + " (" + this.getOccupiedCapacity() + "/"
                + this.getCapacity()
                + ")";
        return toPrint;
    }

    /**
     * Getting the multiplier
     * Provides property multiplier; with default 4
     *
     * @return the truck multiplier
     */
    protected int getMultiplier() {
        return 4;
    }

    /**
     * Getting the volume of this truck
     * Volume = width x height x (length - 1500)
     *
     * @return the volume of this truck
     */
    public double getVolume() {
        double volume = this.getWidth() * this.getHeight() * (this.getLength() - 1500);
        return volume;
    }


}