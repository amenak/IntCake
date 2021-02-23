package arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MergeArrays {

    public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

        // combine the sorted arrays into one large sorted array
    	int [] mergedArr = new int [myArray.length + alicesArray.length];
    	
    	int counter1 = 0;
    	int counter2 = 0;
    	int counter3 = 0;
    	while(counter3 != mergedArr.length) { //OR/AND //i can even use a for loop here.
    		
    		/*// don't like this 
    		int num1 = -1;
    		int num2 = -1; //assuming all numbers are positive*/
    		
    		boolean endMyArray = (counter1 == myArray.length) ? true : false;
    		boolean endAlicesArray = (counter2 == alicesArray.length) ? true : false;
    		
    		//if my and alices array is not finished. i can compare the two numbers
    		//if alices array is finished, add the number from my
    		//if my array is finished, add the number from alice 
  		
    		if(!endMyArray && !endAlicesArray) { //does not work if num2 is -1. 
    			if(myArray[counter1] <= alicesArray[counter2]) {
    				mergedArr[counter3] = myArray[counter1];
    				counter1++;
    			}else {
    				mergedArr[counter3] = alicesArray[counter2];
    				counter2++;
    			}
    		}else if(endMyArray) {
    			mergedArr[counter3] = alicesArray[counter2];
    			counter2++;
    		} else if(endAlicesArray) {
    			mergedArr[counter3] = myArray[counter1]; 
    			counter1++;
    		}
    		
    		counter3++;
    		
    	}
        

        return mergedArr;
    }


















    // tests

    @Test
    public void bothArraysAreEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {};
        final int[] expected = {};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void firstArrayIsEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {1, 2, 3};
        final int[] expected = {1, 2, 3};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void secondArrayIsEmptyTest() {
        final int[] myArray = {5, 6, 7};
        final int[] alicesArray = {};
        final int[] expected = {5, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bothArraysHaveSomeNumbersTest() {
        final int[] myArray = {2, 4, 6};
        final int[] alicesArray = {1, 3, 7};
        final int[] expected = {1, 2, 3, 4, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arraysAreDifferentLengthsTest() {
        final int[] myArray = {2, 4, 6, 8};
        final int[] alicesArray = {1, 7};
        final int[] expected = {1, 2, 4, 6, 7, 8};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MergeArrays.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}