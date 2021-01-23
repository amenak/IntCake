package interviewcake.DynamicProgrammingRecursion;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class CakeThief {

    public static class CakeType {

        final int weight;
        final int value;

        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value  = value;
        }
    }
    
    
    public static class InfinityException extends RuntimeException {
    	public InfinityException () {
    		super("cake value can not be greater than zero");
    	}
    }
    

    public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {
        // calculate the maximum value that we can carry
    	if(cakeTypes.length == 0) return 0; 
    		
    	int [] maxArray = new int [weightCapacity+1];

    	int maxCurrentCapacity = 0; 
    	
    	for(int currentCapacity=1; currentCapacity <= weightCapacity; currentCapacity++) {
    		for(CakeType cake : cakeTypes) {
    			
    			if(cake.weight == 0 && cake.value > 0) {
    				throw new InfinityException();
    			}
    			
    			if(cake.weight <= currentCapacity) {
    				//subtract with cap
    				int maxRemainder = maxArray[currentCapacity - cake.weight]; 
    				maxCurrentCapacity = Math.max(maxCurrentCapacity, cake.value + maxRemainder);
    			}
    		}
    		maxArray[currentCapacity] = maxCurrentCapacity;
    	}
    	
        

        return maxArray[weightCapacity];
    }

//O(N*K) time for n = capacity and k = cake types
//O(N) for space  where n is the maxArray
    













    // tests

    @Test
    public void oneCakeTest() {
        final CakeType[] cakeTypes = {new CakeType(2, 1)};
        final int weightCapacity = 9;
        final long expected = 4;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void twoCakesTest() {
        final CakeType[] cakeTypes = {new CakeType(4, 4), new CakeType(5, 5)};
        final int weightCapacity = 9;
        final long expected = 9;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void onlyTakeLessValuableCakeTest() {
        final CakeType[] cakeTypes = {new CakeType(4, 4), new CakeType(5, 5)};
        final int weightCapacity = 12;
        final long expected = 12;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void lotsOfCakesTest() {
        final CakeType[] cakeTypes = {
            new CakeType(2, 3), new CakeType(3, 6), new CakeType(5, 1),
            new CakeType(6, 1), new CakeType(7, 1), new CakeType(8, 1)
        };
        final int weightCapacity = 7;
        final long expected = 12;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void valueToWeightRatioIsNotOptimalTest() {
        final CakeType[] cakeTypes = {new CakeType(51, 52), new CakeType(50, 50)};
        final int weightCapacity = 100;
        final long expected = 100;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void zeroCapacityTest() {
        final CakeType[] cakeTypes = {new CakeType(1, 2)};
        final int weightCapacity = 0;
        final long expected = 0;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void cakeWithZeroValueAndWeightTest() {
        final CakeType[] cakeTypes = {new CakeType(0, 0), new CakeType(2, 1)};
        final int weightCapacity = 7;
        final long expected = 3;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void cakeWithNonZeroValueAndZeroWeightTest() {
        final CakeType[] cakeTypes = {new CakeType(0, 5)};
        final int weightCapacity = 5;
        
        System.out.println(maxDuffelBagValue(cakeTypes, weightCapacity));
        
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CakeThief.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}