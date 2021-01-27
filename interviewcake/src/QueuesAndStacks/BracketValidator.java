package QueuesAndStacks;

import java.util.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class BracketValidator {

    public static boolean isValid(String code) {

        // determine if the input code is valid
        
    	ArrayDeque<Character> stack = new ArrayDeque<>(); //okay difference between using char/Character object primitive types
    	 
    	for(char c : code.toCharArray()) { //can i loop through string like this?
    		if(c == '{' || c == '(' || c == '[') {
    			stack.push(c);
    		}
    		
    		//better way to represent this?
    		if(c == '}' ) {
    			if(!stack.isEmpty() && stack.peek() == '{')
    			  stack.pop();
    			else {
    			  stack.push(c);
    			}
    		} 
    		
    		if(c == ')') {
    			if(!stack.isEmpty() && stack.peek() == '(')
      			  stack.pop();
      			else {
      			  stack.push(c);
      			}
    		} 
    		
    		if(c == ']') {
    			if(!stack.isEmpty() && stack.peek() == '[')
      			  stack.pop();
      			else {
      			  stack.push(c);
      			} 
    		} 
    		    		
    	}

    	
        return stack.isEmpty(); //if stack is empty return true. if its not return false.
    }


















    // tests

    @Test
    public void validShortCodeTest() {
        final boolean result = isValid("()");
        assertTrue(result);
    }

    @Test
    public void validLongerCodeTest() {
        final boolean result = isValid("([]{[]})[]{{}()}");
        assertTrue(result);
    }

    @Test
    public void mismatchedOpenerAndCloserTest() {
        final boolean result = isValid("([][]}");
        assertFalse(result);
    }

    @Test
    public void interleavedOpenersAndClosersTest() {
        final boolean result = isValid("([)]");
        assertFalse(result);
    }

    @Test
    public void missingCloserTest() {
        final boolean result = isValid("[[]()");
        assertFalse(result);
    }

    @Test
    public void extraCloserTest() {
        final boolean result = isValid("[[]]())");
        assertFalse(result);
    }

    @Test
    public void emptyStringTest() {
        final boolean result = isValid("");
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BracketValidator.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
