package hashTables;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class WordCloudData1 {

    public static class WordCloudData {

        private Map<String, Integer> wordsToCounts = new HashMap<>();

        private void populateWordsToCounts(String inputString) {

            // count the frequency of each word
            if(inputString.isEmpty()) return;
            
        	char [] inputArray = inputString.toCharArray();
        	int wordStartIdx = findWordStartIdx(0, inputArray);
        	
        	//System.out.println(wordStartIdx);
        	
        	for(int i=wordStartIdx; i <= inputArray.length; i++) {
            	//check if its not a letter 
        		if(i == inputArray.length || (!Character.isLetter(inputArray[i]) && inputArray[i] != '-' && inputArray[i] != '\'')) {
        			
        			String word = inputString.substring(wordStartIdx, i);
        			String wordProper = word.substring(0,1).toUpperCase() + word.substring(1);
        			
        			//check map for key- lower case and proper case
        			if(wordsToCounts.containsKey(word.toLowerCase())) {
        				wordsToCounts.put(word.toLowerCase(), wordsToCounts.get(word.toLowerCase()) + 1);
        			}else if (wordsToCounts.containsKey(wordProper)) { //it will be in proper case or other case .. 
        				wordsToCounts.put(wordProper, wordsToCounts.get(wordProper) + 1); //if current word is lowercase, map has propercase it will add. 
        			}else {
        				wordsToCounts.put(word, 1);
        			}
        			
        			//reset word start idx
        			wordStartIdx = findWordStartIdx(i + 1, inputArray);
        			i = wordStartIdx; //iterate from begining of next word
        		}
            }

        }
        
        public int findWordStartIdx(int idx, char [] inputArray) {
        	
        	int startIdx = inputArray.length;
        	
        	for(int i = idx; i < inputArray.length; i++) {
        		if(Character.isLetter(inputArray[i])) {
        			startIdx = i;
        			break; //forgot to exit loop
        		}
        	}
        	
        	return startIdx;
        }

        public WordCloudData(String inputString) {
            populateWordsToCounts(inputString);
        }

        public Map<String, Integer> getWordsToCounts() {
            return wordsToCounts;
        }
    }


















    // tests

    // There are lots of valid solutions for this one. You
    // might have to edit some of these tests if you made
    // different design decisions in your solution.

    @Test
    public void simpleSentenceTest() {
        final String text = "I like cake";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("I", 1);
            put("like", 1);
            put("cake", 1);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    @Test
    public void longerSentenceTest() {
        final String text = "Chocolate cake for dinner and pound cake for dessert";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("and", 1);
            put("pound", 1);
            put("for", 2);
            put("dessert", 1);
            put("Chocolate", 1);
            put("dinner", 1);
            put("cake", 2);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    @Test
    public void punctuationTest() {
        final String text = "Strawberry short cake? Yum!";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("cake", 1);
            put("Strawberry", 1);
            put("short", 1);
            put("Yum", 1);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    @Test
    public void hyphenatedWordsTest() {
        final String text = "Dessert - mille-feuille cake";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("cake", 1);
            put("Dessert", 1);
            put("mille-feuille", 1);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    @Test
    public void ellipsesBetweenWordsTest() {
        final String text = "Mmm...mmm...decisions...decisions";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
                put("mmm", 2);
                put("decisions", 2);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    @Test
    public void apostrophesTest() {
        final String text = "Allie's Bakery: Sasha's Cakes";
        final Map<String, Integer> expected = new HashMap<String, Integer>() { {
            put("Bakery", 1);
            put("Cakes", 1);
            put("Allie's", 1);
            put("Sasha's", 1);
        }};
        final WordCloudData actual = new WordCloudData(text);
        assertEquals(expected, actual.getWordsToCounts());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(WordCloudData1.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}