package jeu;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class ConversionTest {
    Conversion c = new Conversion();

    @Test
    public void testGetCharFromString(){
        assertEquals(c.getCharFromString("A10"), 'A');
        assertNotEquals(c.getCharFromString("A10"), 'B');

        assertEquals(c.getCharFromString("F4"), 'F');
        assertNotEquals(c.getCharFromString("F4"), 'C');
    }

    @Test
    public void testGetIntFromString(){
        assertEquals(c.getIntFromString("A10"), 10);
        assertNotEquals(c.getIntFromString("A10"), 1);

        assertEquals(c.getIntFromString("F4"), 4);
        assertNotEquals(c.getIntFromString("F4"), 5);
    }
}
