package mms.display;

import mms.exceptions.PackingException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.*;
import mms.storage.Bag;
import mms.storage.Box;
import mms.storage.MovingTruck;
import mms.storage.Storage;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * A class to create and display a scenario managed by the MMS (Moving
 * Management System).
 * <p>
 * This can be used to assist in the debugging and visualisation of
 * a scenario and will <b>not</b> be assessed.
 * <p>
 * It is <b>highly recommended</b> you use this sparingly for visualisation
 * purposes and instead write JUnit tests for the majority of your testing.
 * <p>
 * TODO: README
 * You can modify the sample code below to randomly generate test cases for
 * your code.
 * <p>
 * Note that the sample code in the methods below will not compile until you
 * implement most of the required classes for assignment 1.
 * You can uncomment parts of the code once they compile, and you can
 * add more complexity to the code if you wish.
 * <p>
 * Since all the code below is initially commented out, items will be added
 * to the moving truck in the main method.
 * <p>
 * This is to be expected until you start to uncomment code below.
 */
public class SimpleDisplay {

    /**
     * Creates a moving truck, loads and unloads items.
     *
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        MovingTruck truck = new MovingTruck(2000, 2500, 4000, Size.LARGE);

        Storage storage1 = new Box(200, 200, 200, "A box of something");
        System.out.println(storage1.getSize());
        System.out.println(storage1.getOccupiedCapacity());
        System.out.println(storage1.toString());
        Personal personal1 = new Laptop("Thomas", 2);
        Personal personal2 = new Clothes("Lucy", Size.SMALL, ClotheType.SHIRT);
        String clothesPri = "this is the size of small cloth: " + personal2.getWidth() + ", "
                + personal2.getHeight() + ", " + personal2.getLength();
        System.out.println(clothesPri);
        Personal personal3 = new Clothes("Lucy", Size.SMALL, ClotheType.SHIRT);
        Furniture furniture1 = new Furniture(FurnitureType.CHAIR);
        try {
            storage1.pack(personal1);
            storage1.pack(personal2);
            storage1.pack(personal3);
            storage1.pack(furniture1);
            truck.pack((Packable) storage1);
        } catch (PackingException | ClassCastException e) {
            e.printStackTrace();
        }

        storage1 = new Bag(200, 50, 150, Size.LARGE);
        personal1 = new Book("Caleb", "Programming With Java for Dummies",
                false);
        personal2 = new Clothes("Lucy", Size.SMALL, ClotheType.SHIRT);
        personal3 = new Clothes("Lucy", Size.MEDIUM, ClotheType.SOCKS);
        Personal personal4 = new Clothes("Bob", Size.LARGE, ClotheType.PANTS);
        try {
            storage1.pack(personal1);
            storage1.pack(personal2);
            storage1.pack(personal3);
            storage1.pack(personal4);
            truck.pack((Packable) storage1);
        } catch (PackingException | ClassCastException e) {
            e.printStackTrace();
        }

        storage1 = new Box(400, 280, 250, "Materials");
        Storage storage2 = new Box(100, 150, 100, "");
        Storage storage3 = new Bag(100, 50, 100, Size.SMALL);
        furniture1 = new Furniture(FurnitureType.TELEVISION);
        personal1 = new Book("Jane", "Tales from magicians", true);
        personal2 = new Clothes("Barry", Size.SMALL, ClotheType.SHORTS);
        personal3 = new Laptop("Dawn", 3);
        try {
            storage1.pack((Packable) storage2);
            storage1.pack((Packable) storage3);
            storage1.pack(furniture1);
            storage2.pack(personal1);
            storage3.pack(personal2);
            storage3.pack(personal3);
            truck.pack((Packable) storage1);
        } catch (PackingException | ClassCastException e) {
            e.printStackTrace();
        }
        storage1 = new Box(100, 100, 100, "");
        furniture1 = new Furniture(FurnitureType.TELEVISION);
        try {
            storage1.pack(furniture1);
        } catch (PackingException e) {
            e.printStackTrace();
        }

        furniture1 = new Furniture(FurnitureType.BED);
        Furniture furniture2 = new Furniture(FurnitureType.TABLE);
        try {
            truck.pack(furniture1);
            truck.pack(furniture2);
        } catch (PackingException e) {
            e.printStackTrace();
        }
        storage1 = new Box(10, 10, 10, "An empty box");
        try {
            truck.pack((Packable) storage1);
        } catch (PackingException | ClassCastException e) {
            e.printStackTrace();
        }

        try {
            storage1 = new Box(-1, -2, -4, null);
            personal1 = new Laptop("Someone", -1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("After packing:");
        System.out.println(truck.toString(2));
        // remove some items
        for (int i = 0; i < 3; i++) {
            Packable item = truck.unpack();
            System.out.println("Unpacked: " + item.toString());
        }

        System.out.println("After unpacking:");
        System.out.println(truck.toString(2));

    }


}