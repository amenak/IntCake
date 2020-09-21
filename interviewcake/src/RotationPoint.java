import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class RotationPoint {

    public static int findRotationPoint(String[] words) {
    	if(words.length == 0) throw new IllegalArgumentException("not enough words");
    	
    	int start = 0;
    	int end = words.length;
    	int middle = (end-start)/2 + start;
    	
    	while(start < end) {
    		middle = (end-start)/2 + start; 
    		if(words[middle].compareTo(words[middle-1]) < 0) return middle;
    		if(words[middle].compareTo(words[words.length-1]) > 0) start = middle+1;
    		if(words[middle].compareTo(words[words.length-1]) == 0) start = middle+1;
    		if(words[middle].compareTo(words[words.length-1]) < 0) end = middle-1;
    	}
    		
		return middle;
       
    }
    
    
    
    
 


















    // tests

    @Test
    public void smallArrayTest() {
        final int actual = findRotationPoint(new String[] {"cake", "cape"});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int actual = findRotationPoint(new String[] {"grape", "orange", "plum",
            "radish", "apple"});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void largeArrayTest() {
        final int actual = findRotationPoint(
            new String[] {"ptolemaic", "retrograde", "supplant", "undulate", "xenoepist",
            "asymptote", "babka", "banoffee", "engender", "karpatka", "othellolagkage"});
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void possiblyMissingEdgeCaseTest() {
        // are we missing any edge cases?
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RotationPoint.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}