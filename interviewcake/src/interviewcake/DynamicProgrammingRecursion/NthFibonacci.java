package interviewcake.DynamicProgrammingRecursion;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class NthFibonacci {

	//iterative approach
    public static int fib(int n) {
    	if( n < 0) throw new IllegalArgumentException("can not pass in negative number");
    	if( n <= 1 ) return n;
    	
    	int num1 = 1;
    	int num2 = 1;
    	//int sum = num1 + num2; 
    	
    	for(int i=2; i <= n; i++) {
    		int sum = num1 + num2; 
    		num1 = num2;
    		num2 = sum;
    	}
        

        return num2;
    }
    
    //O(N)
    
    //recursive approach
    public static int fib1 (int n) {
    	if( n <= 1) return n;
    	
    	return fib(n-2) + fib(n-1);
    }
    //O(N^2) no O(2^n) every node makes 2 calls and for each other those they make 2 calls
    
    


















    // tests

    @Test
    public void zerothFibonacciTest() {
        final int actual = fib(0);
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void firstFibonacciTest() {
        final int actual = fib(1);
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void secondFibonacciTest() {
        final int actual = fib(2);
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void thirdFibonacciTest() {
        final int actual = fib(3);
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void fifthFibonacciTest() {
        final int actual = fib(5);
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void tenthFibonacciTest() {
        final int actual = fib(10);
        final int expected = 55;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void negativeFibonacciTest() {
        fib(-1);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(NthFibonacci.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}