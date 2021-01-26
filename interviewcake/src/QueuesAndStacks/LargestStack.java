package QueuesAndStacks;

import java.util.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class LargestStack {

    // fill in the definitions for push(), pop(), and getMax()
    

    public static class MaxStack {
    	
    	//need a regular stack and a running max stack
    	private ArrayDeque<Integer> s = new ArrayDeque<>();
    	private ArrayDeque<Integer> maxStack = new ArrayDeque<>();
    	
        public void push(int item) {
        	//when an item comes, we push it on the regular stack and if max stack is empty we add it to the stack. 
        	//otherwise you only add if the current item being pushed in is greater than the top of maxStack 
        	
        	//edge case: can item ever be null? no because you have initialize item
        	
        	s.push(item);
        	if(maxStack.isEmpty() || maxStack.peek() <= item) { //mistake here: item needs to be less than or equal to because what if you get 7,7. the layering needs to match original stack. 
        		maxStack.push(item);
        	}
        }

        public int pop() {
        	//we pop off the regular stack and if item is the max we also pop it off the maxstack. 
        	int item  = s.pop();
        	if(item == maxStack.peek()) {
        		maxStack.pop();
        	}
        	
            return item;
        }

        public int getMax() {
        	
            return maxStack.peek();
        }
    }


















    // tests

    @Test
    public void maxStackTest() {
        final MaxStack s = new MaxStack();
        s.push(5);
        assertEquals("check max after 1st push", 5, s.getMax());
        s.push(4);
        s.push(7);
        s.push(7);
        s.push(8);
        assertEquals("check before 1st pop", 8, s.getMax());
        assertEquals("check pop #1", 8, s.pop());
        assertEquals("check max after 1st pop", 7, s.getMax());
        assertEquals("check pop #2", 7, s.pop());
        assertEquals("check max after 2nd pop", 7, s.getMax());
        assertEquals("check pop #3", 7, s.pop());
        assertEquals("check max after 3rd pop", 5, s.getMax());
        assertEquals("check pop #4", 4, s.pop());
        assertEquals("check max after 4th pop", 5, s.getMax());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LargestStack.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}