import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class findRepeat1 { //my intuitive corrective solution

    public static int findRepeat(int[] numbers) {
    	if (numbers.length < 2) throw new IllegalArgumentException("invalid number of items in array");
    	
    	//find the total range of numbers 
    	//divide the range in half 1..n/2 and n/2+1...n
    	//loop through the input array to find the number of items in the lower range.
    	//are there more items than the number of possibilities in the lower range? 
    	//if yes then divide the range in half again. start=1 and end = mid-1and look for number of items in the array
    	//if no then look at the upper range. 
        int start = 1; //start at 1 because possibilities start from 1..n
        int end = numbers.length-1; //because possibilities 4 for length of 5 so n is 4. 
        int mid = (end-start)/2 + start;
        
        System.out.println("size of array " + end); 
        while(start <= end) {
        	mid = (end-start)/2 + start; 
        	int lRangeStart = start; 
        	int lRangeEnd = mid; 
        	int URangeStart = mid + 1;
        	int URangeEnd = numbers.length-1; 
        	
        	System.out.println("lrS:" + lRangeStart + " lrE:" + lRangeEnd + " UrS:" + URangeStart + " UrE:" + URangeEnd);
        	
        	int itemsInRange = 0;
        	for(int item: numbers) {
        		if(item >= lRangeStart && item <= lRangeEnd) {
        			itemsInRange += 1; 
        		}
        	}
        	
        	
        	int distinctPossibilities = lRangeEnd - lRangeStart+1; //because we're counting number of possibilities not distance
        	System.out.println("items in range: " + itemsInRange + " distinct possibilities " + distinctPossibilities);
        	if(itemsInRange > distinctPossibilities) {
        		start = lRangeStart; 
        		end = lRangeEnd - 1;
        	}else {
        		start = URangeStart;
        		end = URangeEnd; 
        	}
        	
        }
        
        System.out.println("returning mid " + mid);
        return mid;
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
        Result result = JUnitCore.runClasses(findRepeat1.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}