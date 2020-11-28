package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class balancedTree {

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
    
    public static class NodeDepth{
    	BinaryTreeNode n; 
    	int Depth;
    	
    	public NodeDepth(BinaryTreeNode node, int d) {
    		n = node; 
    		Depth = d; 
    	}
    }
    

    public static boolean isBalanced(BinaryTreeNode treeRoot) {
    	//int sum = 0;
    	//int x = travTest(treeRoot);
       // System.out.println("new tree total " + x);
        
    		//visit all the leaf nodes
    		//use a stack to find all the leaf nodes and count the depth. 
    	//base case
    	
    	if(treeRoot.left == null && treeRoot.right == null) return true; //because a tree is balanced if the difference <2 including 0.
    		
    		Deque<NodeDepth> stackNode  = new ArrayDeque<NodeDepth>();
    		stackNode.add(new NodeDepth(treeRoot,0));
    		    		
    		ArrayList<Integer> depths = new ArrayList<Integer>();
    		
    		while(!stackNode.isEmpty()) {
    			NodeDepth nodedepth = stackNode.pop();
    			BinaryTreeNode n = nodedepth.n;
    			int depth = nodedepth.Depth; 
    			
    			//is this node a leaf node? 
    			if(n.left == null && n.right == null) {
    				if(!depths.contains(depth)) {
    					depths.add(depth);
    				}
    				
    				if(depths.size() > 2 || depths.size() == 2 && Math.abs(depths.get(0)-depths.get(1)) >= 2) { 
    				return false;
    				}
    				
    			} else {
    				if(n.left != null) {
    					stackNode.add(new NodeDepth(n.left, depth + 1));
    				}
    				if(n.right != null) {
    					stackNode.add(new NodeDepth(n.right, depth+1));
    				}
    			}
    		}
    	//get the depth of the leaf node, add it to a depth list if it doesn't exist. 
    	//if you have more than 1 different depth, check if the 2 depths are more than 1 leaf node apart. if they're 2 leaf nodes, return false 

        return true;
    }

    //TO DO: 
    //think of what to do if you have 3 different depths. in what scenario would that be possible? 3,4,5

    
    
   /* public static int travTest(BinaryTreeNode root,  int sum) {
    	if(root == null) return 0;
    	
    	System.out.print(root.value + " ");
    	int left = 1 + travTest(root.left);
    	int right = 1 + travTest(root.right);
    	System.out.print("total for this tree" + (left +right));

    	return left+right;
    }*/

















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
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(balancedTree.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}