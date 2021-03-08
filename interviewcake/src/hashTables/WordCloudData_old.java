package hashTables;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class WordCloudData_old {

    public static class WordCloudData {

        private Map<String, Integer> wordsToCounts = new HashMap<>();

        private void populateWordsToCounts(String inputString) {

            //make the entire string lowercase
            if(inputString.length() == 0) return; 
            
            System.out.println("input: " + inputString); 
            
            //inputString = inputString.toLowerCase(); 
            int wordStartIdx = 0;
            
            for(int i=0; i < inputString.length(); i++){
                char c = inputString.charAt(i);
                System.out.print(c);
                
                if(Character.isLetter(c) && i == inputString.length()-1){
                     String word = inputString.substring(wordStartIdx, i+1);
                    //System.out.println(word);
                    
                    if(wordsToCounts.containsKey(word))
                    {
                        wordsToCounts.put(word, wordsToCounts.get(word) +1);
                    }else if (wordsToCounts.containsKey(word.toLowerCase())){
                        wordsToCounts.put(word, wordsToCounts.get(word.toLowerCase()) +1);
                    }else{
                        wordsToCounts.put(word, 1);
                    }
                }
                if(!Character.isLetter(c) && c != '-' && c != '\''){ //multiple spaces? 
                   
                    String word = inputString.substring(wordStartIdx, i);
                    //System.out.println(word);
                    
                    if(wordsToCounts.containsKey(word))
                    {
                        
                        wordsToCounts.put(word, wordsToCounts.get(word) +1);
                        
                        
                        
                    }else if (wordsToCounts.containsKey(word.toLowerCase())){
                        
                        wordsToCounts.put(word, wordsToCounts.get(word.toLowerCase()) +1);
                    }else {
                        
                        //check for uppercase versions
                        char  [] word2 = word.toCharArray(); 
                        word2[0] = Character.toUpperCase(word2[0]);
                        
                        String word3 = new String(word2);
                        
                        if(wordsToCounts.containsKey(word3)){
                          wordsToCounts.put(word3, wordsToCounts.get(word3) +1);   
                        }else{
                          wordsToCounts.put(word, 1);
                        }

                    }
                    
                    for(int j=i; j < inputString.length(); j++){
                        if(Character.isLetter(inputString.charAt(j))){
                            wordStartIdx = j;
                            i=j-1;
                            break;
                        }    
                    }
                    
                }
            }
            

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
        Result result = JUnitCore.runClasses(WordCloudData_old.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}