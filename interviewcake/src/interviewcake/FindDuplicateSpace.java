package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindDuplicateSpace {

    public static int findRepeat(int[] numbers) {

        // find a number that appears more than once in (nlgn)
        //divide the number of possibilities in half to get a range
    	//loop through entire array and check how many items fall within that range.
    	//if there are more items than the number of possibilities within range, then a duplicate number exists inside the range
    	//divide range in half again until you find the duplicate number. 
    	
    	//implement Binary search. 
    	
    	int start= 1; 
    	int end = numbers.length-1; 
    	int midPoint = 0;
    	
    	while(start<=end) {
     		if(start == end ) return start;    		
     		
     		midPoint = ((end-start) + 1)/2; //1/2 = 0. midpoint shouldn't be 0 if theres 2 numbers. 
    		
    		int LowerRangeStart = start;
    		int LowerRangeEnd = start + (midPoint - 1); //0-1 = -1
    		
    		int UpperRangeStart= start + midPoint;
    		int UpperRangeEnd = end;
    		
    		int numberOfItemsUpper = 0;
    		int numberOfItemsLower = 0; 
    		
    		for(int item : numbers) {
    			//yes but if you do item == mid how can you decide to go left or right? you're just counting duplicates for each number at that point. 
    			
    			if(item >= LowerRangeStart && item <= LowerRangeEnd) {
    				numberOfItemsLower++;
    			}else if(item >= UpperRangeStart && item <= UpperRangeEnd){
    				numberOfItemsUpper++; //i am just guessing i don't need to check for upper .. 
    			}
    			
    		} 
    		
    		//how do i return the duplicate number? 
    		if(numberOfItemsUpper > ((UpperRangeEnd-UpperRangeStart) + 1)) {
    			start= UpperRangeStart;
    		}else if(numberOfItemsLower > ((LowerRangeEnd-LowerRangeStart) + 1)) {
    			end=LowerRangeEnd;
    		}
    		
    	}

        return 0;
    }


















    // tests

    @Test
    public void justTheRepeatedNumberTest() {
        final int[] numbers = {1, 1};
        final int expected = 1;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void shortArrayTest() {
        final int[] numbers = {1, 2, 3, 2};
        final int expected = 2;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int[] numbers = {1, 2, 5, 5, 5, 5};
        final int expected = 5;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] numbers = {4, 1, 4, 8, 3, 2, 7, 6, 5};
        final int expected = 4;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindDuplicateSpace.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
