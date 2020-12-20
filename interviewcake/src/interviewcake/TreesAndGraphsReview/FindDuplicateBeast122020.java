package interviewcake.TreesAndGraphsReview;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindDuplicateBeast122020 {

    public static int findDuplicate(int[] intArray) {
    	if(intArray.length < 2) throw new IllegalArgumentException("array can not have less than 2 items to find duplicate");
    	
    	if(intArray.length == 2) {
    		if(intArray[0] == intArray[1]) {
    			return intArray[0];
    		}
    		
    		throw new IllegalArgumentException("Duplicate does not exist"); //is this an illegal argument?
    	}
    	
    	int slowRunner = 0;
    	int fastRunner = 2;
    	
    	while(slowRunner < intArray.length) {
    		if(intArray[slowRunner] == intArray[fastRunner] && fastRunner != slowRunner) {
    			return intArray[slowRunner];
    		}
    		
    		slowRunner++;
			fastRunner++; 
    		
    		if(fastRunner > intArray.length-1)
    			fastRunner=0; //if reached finish line go back to the start 
    	}
        

        throw new IllegalArgumentException("Duplicate does not exist");
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
        Result result = JUnitCore.runClasses(FindDuplicateBeast122020.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
