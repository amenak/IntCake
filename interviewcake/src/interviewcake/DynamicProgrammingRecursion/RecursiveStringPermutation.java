package interviewcake.DynamicProgrammingRecursion;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class RecursiveStringPermutation {

    public static Set<String> getPermutations(String inputString) {
    	//if(inputString.isEmpty()) return new HashSet<String>();
    	
    	Set<String> newSet = new HashSet<String>();
    	if(inputString.length() == 1) {
    		newSet.add(inputString);
    	}
    	
    	if(inputString.length() >= 2) {
	    	Set<String> prevSet = getPermutations(inputString.substring(1)); //cut off first character //will it pass in empty string??
	    	char beginingChar = inputString.charAt(0);
	    	
	    	for(String s : prevSet) {
	    		String word = beginingChar + s; //can i add a char like this to a string
	    		newSet.add(word);
	    		
	    		char [] wordArr = word.toCharArray();
	    		for(int i=0; i < wordArr.length-1; i++) {
	    			char temp = wordArr[i+1];
	    			wordArr[i+1] = wordArr[i];
	    			wordArr[i] = temp;
	    			//how do i change the character at specific index in a string - i converted it to a character array
	    			newSet.add(wordArr.toString());
	    		}
	    	}
    	}
      
         
        return newSet;
    }


















    // tests

    @Test
    public void emptyStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList(""));
        final Set<String> actual = getPermutations("");
        assertEquals(expected, actual);
    }

    @Test
    public void oneCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("a"));
        final Set<String> actual = getPermutations("a");
        assertEquals(expected, actual);
    }

    @Test
    public void twoCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("ab", "ba"));
        final Set<String> actual = getPermutations("ab");
        assertEquals(expected, actual);
    }

    @Test
    public void threeCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("abc", "acb", "bac", "bca",
                                                                 "cab", "cba"));
        final Set<String> actual = getPermutations("abc");
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RecursiveStringPermutation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}