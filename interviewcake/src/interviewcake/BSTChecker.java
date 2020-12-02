package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class BSTChecker {

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

    public static boolean isBinarySearchTree(BinaryTreeNode root) {
    	if(root == null) return true;
    	
    	boolean left = true;
    	boolean right = true;
    	
    	if(root.left != null && root.left.value < root.value ) {
    		left = bstChecker(root.left, Integer.MIN_VALUE, root.value);
    		//System.out.print("left" + left);
    		
    	}
    	
    	if(root.right != null && root.right.value > root.value) {
    		right = bstChecker(root.right, root.value, Integer.MAX_VALUE);
    		//System.out.print("right" + right);    		
    	}
    	
    	//System.out.println("\n" + (right&&left));
        return (left && right);
    }

    public static boolean bstChecker(BinaryTreeNode node, int lowerRange, int upperRange) {
    	if(node == null) return true;
    	
    	if(node.value < lowerRange || node.value > upperRange) {
    		return false;
    	}
    	
    	boolean l = bstChecker(node.left, lowerRange, node.value);
		boolean r = bstChecker(node.right, node.value, upperRange);
		
    	return l && r;
    }


/*mistakes: only visited the children node if the current node was valid, which is fine but would be a different implementation.
 * i was not saving the result of the children node and only returning the result of the current node. so you must pass the children node value up somehow. 
 * which is why i saved the result and checked if the current node AND its children were valid. 
 * -the other mistake was using AND instead of OR to check the ranges. 
 *-i also initially compared each node to only the parent. but for BST, ever node on the left needs to be less than the root and greater than the root on the right. 
 */
   













    // tests

    @Test
    public void validFullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void bothSubtreesValidTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(20);
        a.insertRight(60);
        final BinaryTreeNode b = root.insertRight(80);
        b.insertLeft(70);
        b.insertRight(90);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void outOfOrderLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(70).insertRight(60).insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void oneNodeTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BSTChecker.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}