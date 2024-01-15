package mms.personal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BookTest {
    // initiate demo book for testing
    private Book nonfictionBook = new Book("Miki","Biology", false);
    private Book fictionBook = new Book("Raf", "In the wild", true);

    // test error throws in constructor
    @Test
    public void testConstructionException() {
        //case title is empty
        try {
            Personal failBook = new Book("Linh", "", true);
            System.out.println("Test empty owner failed");
        } catch (IllegalArgumentException e) {
            //System.out.println("Pass title is empty string test");
        }

        // case owner is null
        try {
            Personal failBook = new Book(null, "Harry Potter", true);
            System.out.println("Test null owner failed");
        } catch (IllegalArgumentException e) {
            //System.out.println("Pass owner is null test");
            assertEquals(e.getClass().getSimpleName(), "IllegalArgumentException");
        }

        //case owner is empty string
        try {
            Personal failbook = new Book("", "Harry Potter", true);
            System.out.println("Test empty owner failed");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getClass().getSimpleName(), "IllegalArgumentException");
            //System.out.println("Pass owner is empty string test");
        }
    }

    // test getTitle()
    @Test
    public void testGetTitle() {
        assertEquals(nonfictionBook.getTitle(), "Biology");
        assertEquals(fictionBook.getTitle(), "In the wild");
    }

    //test getOwner()
    @Test
    public void testGetOwner() {
        assertEquals(nonfictionBook.getOwner(), "Miki");
        assertEquals(fictionBook.getOwner(), "Raf");
    }

    // test toString()
    @Test
    public void testToString() {
        assertEquals(nonfictionBook.toString(), "Book (Miki) Title: Biology (Non-Fiction)");
        assertEquals(fictionBook.toString(), "Book (Raf) Title: In the wild (Fiction)");
    }

    // checking for book measurements
    @Test
    public void testGetBookMeasurement() {
        //width is 20.0 cm, height is 20.0cm length is 5.0cm
        double bookWidth = 20.;
        double bookHeight = 20.;
        double bookLength = 5.;
        assertTrue(Math.abs(nonfictionBook.getWidth() - bookWidth) == 0);
        assertTrue(Math.abs(nonfictionBook.getHeight() - bookHeight) == 0);
        assertTrue(Math.abs(nonfictionBook.getLength() - bookLength) == 0);
    }


}