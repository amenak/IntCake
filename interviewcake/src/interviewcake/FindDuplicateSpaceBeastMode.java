package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindDuplicateSpaceBeastMode {

	// find a number that appears more than once ... in O(n) time
    public static int findDuplicate(int[] intArray) {
    	//check if array has atleast length 3, otherwise check the 2 elements and return null or false; 
        if(intArray.length < 2) throw new IllegalArgumentException("Array must contain 2 elements to find duplicates.");
        
        if(intArray.length ==  2) {
        	if(intArray[0] == intArray[1]) {
        		return intArray[0];
        	}else {
        		return -1;
        	}
        } 
        
        
    	int runner1 = 0;
    	int runner2 = 2;
    	
    	while(runner1 < intArray.length) {
    		if(intArray[runner1] == intArray[runner2] && runner1 != runner2) {
    			return intArray[runner1];
    		}
    		
    		if(runner2 < intArray.length) {
    			runner2++;
    		}else {
    			runner2 = 0;
    		}
    		
    		runner1++;
    	}
    	
        return -1;
    }


















    // tests

    @Test
    public void justTheRepeatedNumberTest() {
        final int[] numbers = {1, 1};
        final int expected = 1;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void shortArrayTest() {
        final int[] numbers = {1, 2, 3, 2};
        final int expected = 2;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int[] numbers = {1, 2, 5, 5, 5, 5};
        final int expected = 5;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] numbers = {4, 1, 4, 8, 3, 2, 7, 6, 5};
        final int expected = 4;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindDuplicateSpaceBeastMode.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}