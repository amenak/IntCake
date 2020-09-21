import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class highestproduct3 {

    public static int highestProductOf3(int[] intArray) {
        
        if(intArray.length < 3) throw new IllegalArgumentException("less than 3 parms");

        int idx1 = intArray[0];
        int idx2 = intArray[1];
        int idx3 = intArray[2];
       
        int hp3 = intArray[0] * intArray[1] * intArray[2];
        
        int highest = Math.max(Math.max(idx1, idx2), idx3);
        int lowest = Math.min(Math.min(idx1, idx2), idx3);
        int hp2 = Math.max(Math.max((idx1*idx2), (idx1*idx3)), (idx2*idx3));
        int lp2 = Math.min(Math.min((idx1*idx2), (idx1*idx3)), (idx2*idx3));
        
        for(int i=3; i < intArray.length; i++){
            
            int curr = intArray[i];
            //set hp3 - don't multiple by itself
            int maxHP3 = Math.max((lp2 * curr), (hp2 * curr));
            hp3 = Math.max(maxHP3, hp3); //mistake? what if highest doesnot change
            
             //set hp2
            hp2 = Math.max((highest * curr), hp2);
            //set lp2
            lp2 = Math.min((lowest * curr), lp2);
            //set the highest
            highest = Math.max(curr, highest);
            //set the lowest
            lowest = Math.min(curr, lowest);
            
           
        }
        

        return hp3;
    }


















    // tests

    @Test
    public void shortArrayTest() {
        final int actual = highestProductOf3(new int[] {1, 2, 3, 4});
        final int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    public void longerArrayTest() {
        final int actual = highestProductOf3(new int[] {6, 1, 3, 5, 7, 8, 2});
        final int expected = 336;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasOneNegativeTest() {
        final int actual = highestProductOf3(new int[] {-5, 4, 8, 2, 3});
        final int expected = 96;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasTwoNegativesTest() {
        final int actual = highestProductOf3(new int[] {-10, 1, 3, 2, -10});
        final int expected = 300;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayIsAllNegativesTest() {
        final int actual = highestProductOf3(new int[] {-5, -1, -3, -2});
        final int expected = -6;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyArrayTest() {
        highestProductOf3(new int[] {});
    }

    @Test(expected = Exception.class)
    public void exceptionWithOneNumberTest() {
        highestProductOf3(new int[] {1});
    }

    @Test(expected = Exception.class)
    public void exceptionWithTwoNumbersTest() {
        highestProductOf3(new int[] {1, 1});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(highestproduct3.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
