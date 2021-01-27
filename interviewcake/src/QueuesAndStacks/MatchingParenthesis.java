package QueuesAndStacks;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MatchingParenthesis {

    public static int getClosingParen(String sentence, int openingParenIndex) {
    	
    	
        // find the position of the matching closing parenthesis
    
    	int parenthesisCounter = 0;
        for(int i=openingParenIndex; i <sentence.length(); i++) {
        	if(sentence.charAt(i) == '(') {
        		parenthesisCounter++;
        	}
        	if(sentence.charAt(i) == ')') {
        		parenthesisCounter--;
        	}
        	
        	if(parenthesisCounter == 0) {
        		return i;
        	}
        }
        
        throw new IllegalArgumentException("Parenthesis has no closer");
        
    }
    
   //O(N) TIME
    //O(1) SPACE


















    // tests

    @Test
    public void allOpenersThenClosersTest() {
        final int expected = 7;
        final int actual = getClosingParen("((((()))))", 2);
        assertEquals(expected, actual);
    }

    @Test
    public void mixedOpenersAndClosersTest() {
        final int expected = 10;
        final int actual = getClosingParen("()()((()()))", 5);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void noMatchingCloserTest() {
        getClosingParen("()(()", 2);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MatchingParenthesis.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}