package QueuesAndStacks;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MatchingParenthesis {

    public static int getClosingParen(String sentence, int openingParenIndex) {
    	
    	
        // find the position of the matching closing parenthesis
    	
    	//String tracker = "";
    	StringBuilder parenthesis = new StringBuilder();
    	
        for(int i=openingParenIndex; i <sentence.length(); i++) {
        	if(sentence.charAt(i) == '(') {
        		parenthesis.append("("); //string builder amortizes to O(1)
        		//tracker += "("; can take up to n^2
        	}
        	if(sentence.charAt(i) == ')' && parenthesis.length() != 0) {
        		//String chopped = parenthesis.substring(0, parenthesis.length()-1); //how do you replace the current string builder..
        		parenthesis.deleteCharAt(parenthesis.length()-1);
        		//tracker = tracker.substring(0, tracker.length()-1);
        	}
        	
        	if(parenthesis.length() == 0) {
        		return i;
        	}
        }
        
        if(parenthesis.length() != 0)
        	throw new IllegalArgumentException("Parenthesis has no closer");
        
        return -1;

    }


















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