package arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class reverseWords {

    public static void reverseWords1(char[] message) {
    	if(message.length == 0 ) return;
    	
    	//reverse entire array
    	revWordsHelper(0, message.length-1, message);
    	
    	//reverse every word
    	int wordStartIdx = 0;
        for(int i=0; i <= message.length; i++) {
        	if(i == message.length || message[i] == ' ') {
        		revWordsHelper(wordStartIdx, i-1, message);
        		wordStartIdx = i+1;
        	}
        	
        	//misses last word 
        	/*if(i == message.length-1) {
        		revWordsHelper(wordStartIdx, i, message); //should also reverse back a single word
        	}*/
        	
        }
        
        

    }
    
    //is the array pass by reference or value?
    public static void revWordsHelper(int startIdx, int endIdx, char [] msg) {
    	if ((endIdx-startIdx) < 1) return;
    	
    	int mid = startIdx + (endIdx-startIdx)/2; //mistake here
    	
    	for(int i=startIdx; i <= mid; i++) {
    		char temp = msg[i];
    		msg[i] = msg[endIdx-(i-startIdx)]; //mistake here
    		msg[endIdx-(i-startIdx)] = temp;
    	}
    }



//Time O(N)














    // tests

    @Test
    public void oneWordTest() {
        final char[] expected = "vault".toCharArray();
        final char[] actual = "vault".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoWordsTest() {
        final char[] expected = "cake thief".toCharArray();
        final char[] actual = "thief cake".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void threeWordsTest() {
        final char[] expected = "get another one".toCharArray();
        final char[] actual = "one another get".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void multipleWordsSameLengthTest() {
        final char[] expected = "the cat ate the rat".toCharArray();
        final char[] actual = "rat the ate cat the".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void multipleWordsDifferentLengthsTest() {
        final char[] expected = "chocolate bundt cake is yummy".toCharArray();
        final char[] actual = "yummy is cake bundt chocolate".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void emptyStringTest() {
        final char[] expected = "".toCharArray();
        final char[] actual = "".toCharArray();
        reverseWords1(actual);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(reverseWords.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}