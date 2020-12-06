package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class secondLargestBST {

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

    /*public static int findLargest(BinaryTreeNode rootNode) {

        // find the second largest item in the binary search tree
    	if(rootNode == null) throw new IllegalArgumentException ("empty tree"); //it should be an exception for an empty tree
    	if(rootNode.right == null && rootNode.left == null ) throw new IllegalArgumentException("no second node in tree"); //why cant we just return the root? OH because its not the second largest
    	
    	if(rootNode.right == null && rootNode.left != null) return secondMaxLeft(rootNode.left);
    			
    	return secondMaxRight(rootNode);
    }
    
    
    public static int secondMaxRight(BinaryTreeNode node) {
    	
    	BinaryTreeNode rightChild = node.right;
    	int max = node.value; 
    	
    	if(rightChild.right != null) { 
    		return secondMaxRight(node.right);
    	}
    	// if this highest node has a left subtree, the second highest is in there. else return this nodes parent
    	//we can either be at the parent or we can save the value of the parent. i decided to be at the parent. 
    
    	if(rightChild.left != null) {
    		max = secondMaxLeft(rightChild.left);
    		//problem is this stops at the parent. but i need the right node. how do i do that? i get confused.
    		//i just created another method that goes all way to the right node from this node. 
    	}
    	
    	return max; //how do you return the parent? change the base statement to stop 2 before the leaf
    }
    
    public static int  secondMaxLeft(BinaryTreeNode node) {
    	if(node.right != null) {
    		return secondMaxLeft(node.right);
    	}
    	
    	return node.value; 
    }*/
    
    
    //iterative approach cleaner - does not pass test idk y
    public static int findSecondLargest1(BinaryTreeNode rootNode) {

        // find the second largest item in the binary search tree
        if(rootNode == null || (rootNode.left == null && rootNode.right == null)){
            throw new IllegalArgumentException("Invalid Tree");
        }
        
        //get to the right most node (largest node) and return parent if node has no left subtree
        //if there is left subtree, return the largest node 
        
        //int parentValue = rootNode.value; //you can remove this and always be at the parent node so you could return the value
        
        BinaryTreeNode curr = rootNode;
        
        while(true){ //true works because 
            
        	BinaryTreeNode rightChild = curr.right;
        	System.out.println(curr.value + " " + curr.left.value + " ihi" + curr.right.value);
        	
        	
        	
        	if( curr.left != null && curr.right == null) {
        		System.out.print(curr.right.value + ", ");
        	 return findLargest(curr.left);	
        	}
        	
            if (rightChild.right == null){ // parent node of largest node reached 
                
                //check if it has left subtree
                if(rightChild.left != null){
                    return findLargest(rightChild.left);
                }
               
                return curr.value;
            }
            
            if(rightChild.right == null ) {
            	return curr.value;
            }

            
            curr = curr.right;
        }
        
       
        
    }

    public static int findLargest(BinaryTreeNode node){
        
        //recurse all the way to the right most node. stop before you hit a null node
        if(node.right != null){
            return findLargest(node.right); 
        }
        
        return node.value; 
        
    }
    
    
    //Brute Force takes O(N) time to visit each node and O(2N) space for call stack and array
    public static int findSecondLargest(BinaryTreeNode rootNode) {
    	if(rootNode == null || (rootNode.left == null && rootNode.right == null)) {
    		throw new IllegalArgumentException("Tree is invalid.");
    	}
    	List<Integer> arr = new ArrayList<Integer>();
    	//fill array
    	InOrderTraversal(rootNode, arr);
    	return arr.get(arr.size()-2);
    }
    
    public static void InOrderTraversal(BinaryTreeNode Node, List<Integer> arr) {
    	if(Node == null) return; 
    	
    	InOrderTraversal(Node.left, arr);
    	arr.add(Node.value);
    	InOrderTraversal(Node.right, arr);
    }
    
    // tests

    @Test
    public void findSecondLargestTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final int actual = findSecondLargest1(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftChildTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70).insertLeft(60);
        final int actual = findSecondLargest1(root);
        final int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftSubtreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70).insertLeft(60);
        b.insertLeft(55).insertRight(58);
        b.insertRight(65);
        final int actual = findSecondLargest1(root);
        final int expected = 65;
        assertEquals(expected, actual);
    }

    @Test
    public void secondLargestIsRootNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70);
        final int actual = findSecondLargest1(root);
        final int expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    public void descendingLinkedListTest() {
    	System.out.print("ey");
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20);
        final int actual = findSecondLargest1(root);
        final int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    public void ascendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(60).insertRight(70).insertRight(80);
        final int actual = findSecondLargest1(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithTreeThatHasOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        findSecondLargest1(root);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyTreeTest() {
        findSecondLargest1(null);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(secondLargestBST.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}