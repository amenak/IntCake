package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;
import java.util.*;

public class BalancedTree121320 {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }
    
    public static class NodePair{ //static class because static method needs access to it
    	BinaryTreeNode node;
    	int depth;
    	
    	public NodePair(BinaryTreeNode n, int d) {
    		node = n;
    		depth = d;
    	}
    	
    }
    
    //iterative approach
    public static boolean isBalanced1(BinaryTreeNode treeRoot) {
    	if(treeRoot == null) return true;
    	
    	List<Integer> depths = new ArrayList<Integer>();
    	Deque<NodePair> nodesVisited = new ArrayDeque<NodePair>();
    	nodesVisited.add(new NodePair(treeRoot, 0));
    	
    	while(!nodesVisited.isEmpty()) {
    		NodePair n = nodesVisited.pop();
    		BinaryTreeNode node = n.node;
    		int depth = n.depth;

    		//leaf node 
    		if(node.left == null && node.right == null) {
    			if(!depths.contains(depth)) { //O(n) to check for contains but thats okay because it wont be more than 2-3 elements
    				depths.add(depth);
    			}
    			
    			if(depths.size() > 2 || depths.size() == 2 && Math.abs(depths.get(0)-depths.get(1)) > 1) {
    				return false;
    			}
	
    		}else {
    		
	    		if(node.left != null) {
	    			//DEPTH++ will not work!!
	    			nodesVisited.add(new NodePair(node.left, depth + 1)); 
	    		}
	    		
	    		if(node.right != null) {
	    			nodesVisited.add(new NodePair(node.right, depth + 1));
	    		}
    		
    		}
    	}
    	
    	
    	return true;
    }
    
    
    //recursive approach - doesnot work for linear binary tree
    public static boolean isBalanced(BinaryTreeNode treeRoot) {
    	if(treeRoot == null) return true;
    		
        return checkHeightBalanced(treeRoot);
    }
    
    public static boolean checkHeightBalanced(BinaryTreeNode node) { //to pass the linked list test you need to check if the current node is a leaf node
    	if(node == null) return true;

    	int left = FindHeight(node.left);
    	int right = FindHeight(node.right);
    	
    	
    	if(Math.abs(left-right) > 1) 
    		return false;
    	
    	return checkHeightBalanced(node.left) && checkHeightBalanced(node.right);
    }
    
    public static int FindHeight(BinaryTreeNode node) {
    	if(node == null) return 0;
    	
    	int left = FindHeight(node.left);
    	int right = FindHeight(node.right);
    	
    	return 1 + Math.max(left, right); 
    	
    }


















    // tests

    @Test
    public void fullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(5);
        final BinaryTreeNode a = root.insertLeft(8);
        final BinaryTreeNode b = root.insertRight(6);
        a.insertLeft(1);
        a.insertRight(2);
        b.insertLeft(3);
        b.insertRight(4);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void bothLeavesAtTheSameDepthTest() {
        final BinaryTreeNode root = new BinaryTreeNode(3);
        root.insertLeft(4).insertLeft(1);
        root.insertRight(2).insertRight(9);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByOneTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByTwoTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7).insertRight(8);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void bothSubTreesSuperbalancedTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertLeft(5);
        final BinaryTreeNode b = root.insertRight(9);
        b.insertLeft(8).insertLeft(7);
        b.insertRight(5);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void bothSubTreesSuperbalancedTwoTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final BinaryTreeNode a = root.insertLeft(2);
        a.insertLeft(3);
        a.insertRight(7).insertRight(8);
        root.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void threeLeavesAtDifferentLevelsTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final BinaryTreeNode a = root.insertLeft(2);
        final BinaryTreeNode b = a.insertLeft(3);
        a.insertRight(4);
        b.insertLeft(5);
        b.insertRight(6);
        root.insertRight(7).insertRight(8).insertRight(9).insertRight(10);
        final boolean result = isBalanced(root);
        assertFalse(result);          
    }

    @Test
    public void onlyOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void treeIsLinkedListTest() {
    	
        final BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertRight(2).insertRight(3).insertRight(4);
        final boolean result = isBalanced(root);
        System.out.println("treeIsLinkedListTest " + result);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BalancedTree121320.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
