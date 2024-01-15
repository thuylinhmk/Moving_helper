package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.utility.Packable;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovingTruckTest {

    // initiate some truck to test
    private MovingTruck defaultTruck, smallTruck, mediumTruck;
    private List<Packable> listPackedItems = new ArrayList<Packable>();

    private int truckMultiplier = 4;

    // construct some items
    private Box demoBox = new Box(200, 300, 500, "empty box");
    private Bag demoBag = new Bag(10, 10, 10);
    private Box hugeBox = new Box (1000., 2000, 3500., "");
    private Furniture demoTv = new Furniture(FurnitureType.TELEVISION);
    private Furniture demoChairs = new Furniture(FurnitureType.CHAIR);

    @Before
    public void constructEmptyTruck() {
        defaultTruck = new MovingTruck(6000., 7000., 8000.);
        smallTruck = new MovingTruck(2000., 3000., 4000, Size.SMALL);
        mediumTruck = new MovingTruck(3000., 4000., 5000., Size.MEDIUM);

        listPackedItems = new ArrayList<Packable>();
    }

    // test error throw constructor
    @Test
    public void testConstructor() {
        // length less than 1500 with no size argument
        try {
            MovingTruck demoTruck = new MovingTruck(3000, 3000, 1000);
            fail("Constructor test failed, error Length < 1500 cm");
        } catch (IllegalArgumentException e){
            //System.out.println("Constructor test passed");
        }

        // length less than 1500 with size argument
        try {
            MovingTruck demoTruck = new MovingTruck(3000, 4000, 1000, Size.SMALL);
            fail("Constructor test failed, error Length < 1500 cm");
        } catch (IllegalArgumentException e) {
            //System.out.println("Constructor test passed");
        }
    }

    // test getSize()
    @Test
    public void testGetSize() {
        assertEquals(smallTruck.getSize(), Size.SMALL);
        assertEquals(mediumTruck.getSize(), Size.MEDIUM);
        assertEquals(defaultTruck.getSize(), Size.LARGE);
    }

    //test getVolume
    @Test
    public void testGetVolume() {
        double volume = 6000.0 * 7000.0 * (8000.0-1500);
        assertTrue(Math.abs(defaultTruck.getVolume() - volume) == 0);
    }

    // test get measurement
    @Test
    public void testGetMeasurement() {
        double width = 6000.;
        double height = 7000.;
        double length = 8000.;
        // getWidth
        assertTrue(Math.abs(defaultTruck.getWidth() - width) == 0);
        assertTrue(Math.abs(defaultTruck.getHeight() - height) == 0);
        assertTrue(Math.abs(defaultTruck.getLength() - length) == 0);
    }

    // test getMultiplier
    @Test
    public void testGetMultiplier() {
        assertTrue(Math.abs(defaultTruck.getMultiplier() - truckMultiplier) == 0);
    }

    // test getCapcity
    @Test
    public void testGetCapacity() {
        int smallCapacity = 3*truckMultiplier;
        int mediumCapacity = 5*truckMultiplier;
        int largeCapacity = 10*truckMultiplier;

        assertTrue(Math.abs(smallTruck.getCapacity() - smallCapacity) == 0);
        assertTrue(Math.abs(mediumTruck.getCapacity() - mediumCapacity) == 0);
        assertTrue(Math.abs(defaultTruck.getCapacity() - largeCapacity) == 0);
    }


    // test pack
    @Test
    public void testPack() {
        // add a package item
        listPackedItems.add(demoBox);
        try {
            smallTruck.pack(demoBox);
            assertEquals(smallTruck.getElements(), listPackedItems);
        } catch (PackingException e) {
            fail("Packing test failed");
        }

        //try to add two furniture
        listPackedItems.add(demoTv);
        listPackedItems.add(demoChairs);
        try {
            smallTruck.pack(demoTv);
            smallTruck.pack(demoChairs);
            assertEquals(smallTruck.getElements(), listPackedItems);
        } catch (PackingException e) {
            fail("Packing test failed");
        }

        // try to add normal item after adding 2 furniture
        try {
            smallTruck.pack(demoBag);
            fail("Testing adding normal item while truck has furniture failed");
        } catch (PackingException e) {
            assertEquals(e.getClass().getSimpleName(), "PackingOrderException");
        }

        //throw error when adding item causes over measurements
        try {
            smallTruck.pack(hugeBox);
            fail("Testing packing oversize item failed");
        } catch (PackingException e) {
            assertEquals(e.getClass().getSimpleName(), "StorageFullException");
        }

        //throw error when adding more than capacity
        try {
            for (int numberItem = 0; numberItem <= 40; numberItem++) {
                mediumTruck.pack(demoBag);
            };
            fail("Testing add more than capacity failed");
        } catch (PackingException e) {
            assertEquals(e.getClass().getSimpleName(), "StorageFullException");
        }
    }

    // test unpack
    @Test
    public void testUnpack() {
        // try unpacking things
        try {
            // add things
            smallTruck.pack(demoBox);
            smallTruck.pack(demoBag);
            smallTruck.pack(demoTv);

            // remove thing
            assertEquals(smallTruck.unpack(), demoTv);
            assertEquals(smallTruck.unpack(), demoBag);
        } catch (PackingException e) {
            //fail("Failed unpack test");
        }

        // try unpacking empty truck
        assertEquals(mediumTruck.unpack(), null);
    }

    // test getOccupiedCapacity
    @Test
    public void testGetOccupiedCapacity() {
        assertTrue(Math.abs(smallTruck.getOccupiedCapacity() - 0) == 0);
        try {
            smallTruck.pack(demoBag);
            smallTruck.pack(demoChairs);
            assertEquals(smallTruck.getOccupiedCapacity(), 2);
        } catch (PackingException e) {
            fail("Testing getOccupiedCapacity failed");
        }
    }

    // test toString()
    @Test
    public void testToString() {
        try {
            smallTruck.pack(demoBag);
            smallTruck.pack(demoTv);
        } catch (PackingException e) {
        }
        assertEquals(smallTruck.toString(), "MovingTruck (2/12)");
    }

    //test toString(int level)
    @Test
    public void testToStringInt() {
        try {
            demoBox.pack(demoTv);
            smallTruck.pack(demoBag);
            smallTruck.pack(demoBox);
            smallTruck.pack(demoChairs);
        } catch (PackingException e) {
        }

        int level = 2;
        String correctString = "\t\t" + "MovingTruck (3/12)" + System.lineSeparator() + "\t\t\t"
                + demoBag.toString() + System.lineSeparator() + "\t\t\t" + demoBox.toString()
                + System.lineSeparator() + "\t\t\t\t" + demoTv.toString()
                + System.lineSeparator() + "\t\t\t" + demoChairs.toString();

        assertEquals(smallTruck.toString(level), correctString);
    }


}