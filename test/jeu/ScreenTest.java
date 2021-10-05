package jeu;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

import java.util.ArrayList;

public class ScreenTest {
    @Test
    public void testGetStringFromInt(){
        assertEquals(new Screen(10).getStringFromInt(1,0), "A1");
        assertEquals(new Screen(10).getStringFromInt(1,1), "A2");
        assertEquals(new Screen(10).getStringFromInt(2,0), "B1");
        assertEquals(new Screen(10).getStringFromInt(2,1), "B2");

        assertNotEquals(new Screen(10).getStringFromInt(1,0), "B1");
        assertNotEquals(new Screen(10).getStringFromInt(2,0), "A1");
    }

    @Test
    public void testGetPositionOfLetter(){
        assertEquals(new Screen(10).getPositionOfLetter('A'), 0);
        assertEquals(new Screen(10).getPositionOfLetter('B'), 1);
        assertEquals(new Screen(10).getPositionOfLetter('C'), 2);

        assertNotEquals(new Screen(10).getPositionOfLetter('A'), 1);
        assertNotEquals(new Screen(10).getPositionOfLetter('B'), 4);
    }

    @Test
    public void testGetCaseFromString(){
        Screen s = new Screen(10);
        s.clear();
        assertEquals(s.getNbCoupsJoues(), 0);
        s.setPoint('A',1,'X');  //add a point on A1
        assertEquals(s.getNbCoupsJoues(), 1);
        s.setPoint('A',2,'X');  //add a point on A2
        s.setPoint('A',3,'X');  //add a point on A3
        assertEquals(s.getNbCoupsJoues(), 3);
        assertNotEquals(s.getNbCoupsJoues(), 4);
    }

    @Test
    public void testCheckUserInput(){
        Screen s = new Screen(10);
        s.clear();
        s.setPoint('A', 1, 'X');  //add a point on A1
        ArrayList<Case> allPossiblePlays = s.getAllPossiblePlays(true);

        assertTrue(s.checkUserInput('A',2));
        assertTrue(s.checkUserInput('B',1));

        assertFalse(s.checkUserInput('F',1));
        assertFalse(s.checkUserInput('B',3));
    }

    @Test
    public void testCaseListToStringList(){
        Screen s = new Screen(10);
        s.clear();
        s.setPoint('A', 1, 'X');  //add a point on A1
        ArrayList<Case> allPossiblePlays = s.getAllPossiblePlays(true);
        List<String> allPossiblePlayString = s.caseListToStringList(allPossiblePlays);

        List<String> list = new ArrayList<>();
        list.add("A2");
        list.add("B1");
        list.add("B2");

        assertTrue(allPossiblePlayString.equals(list));

        //False
        list.add("A2");
        list.add("B1");
        list.add("B2");
        list.add("B3");

        assertFalse(allPossiblePlayString.equals(list));
    }

    @Test
    public void testGetAllPossiblesPlays() {
        Screen s = new Screen(10);
        s.clear();
        s.setPoint('A', 1, 'X');  //add a point on A1
        ArrayList<Case> allPossiblePlays = s.getAllPossiblePlays(true);
        List<String> allPossiblePlayString = s.caseListToStringList(allPossiblePlays);
        List<String> list = new ArrayList<>();
        list.add("A2");
        list.add("B1");
        list.add("B2");

        assertTrue(allPossiblePlayString.equals(list));

        //False
        list.add("A2");
        list.add("B1");
        list.add("B2");
        list.add("B3");

        assertFalse(allPossiblePlayString.equals(list));
    }
}
