package interviewcake.TreesAndGraphsReview;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindDuplicateBeast122720 {

    public static int findDuplicate(int[] intArray) {
    	if(intArray.length < 2 ) throw new IllegalArgumentException("array must contain more than one element to find duplicate");
    	
    	//1. get inside the cycle.  go all the way to end of the list 
    	int n = intArray.length - 1; //n is the number of unique elements in the list, n + 1 would be the length
    	
    	int positionIncycle = n + 1; //the last position in the cycle is the number of unique elements + 1. 5 unique elements, 6 positions. 
    	
    	for(int i=0; i < n; i++) {
    		positionIncycle = intArray[positionIncycle - 1 ]; //to access the index in the array, index = position - 1; because positions start from 1 not 0
    	}
    	
    	
    	//2. get the length of the cycle. by counting how many times do you need to go across to get to the same node 
    	
    	int rememberedPosition = positionIncycle;
    	int cyclePosition = intArray[rememberedPosition - 1]; //one step ahead
    	int counter = 1; //start from 1 to count the step ahead 
    	
    	while(cyclePosition != rememberedPosition) {
    		cyclePosition = intArray[cyclePosition - 1];
    		counter+=1;
    	}
    	
    	//3. start from the begining of the cycle with a slow pointer and a fast pointer. move them one at a time until they both meet. 
    	int slowPointerPos = n+1;//start at the head of the list. remember this is the position number not the value of the element in that position. 
    	int fastPointerPos = n+1; 
    	for(int i=0; i < counter; i++) {
    		fastPointerPos = intArray[fastPointerPos-1]; //moves the length amount. alittle confused about visualizing it here
    	}
    	
    	while(fastPointerPos != slowPointerPos) {
    		fastPointerPos = intArray[fastPointerPos -1];
    		slowPointerPos = intArray[slowPointerPos -1];
    	}
    	//4. return the slow pointer 
        

        return slowPointerPos;
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
        Result result = JUnitCore.runClasses(FindDuplicateBeast122720.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}