package interviewcake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    
    public static class NodePair{
        BinaryTreeNode _Node; 
        int _Depth; 
        
        public NodePair(BinaryTreeNode node, int depth){
            _Node = node;
            _Depth = depth;
        }
    }

    public static boolean isBalanced(BinaryTreeNode treeRoot) {
        
        if (treeRoot == null) return true; 
        //if (treeRoot.left == null && treeRoot.right == null) return true; 
        // determine if the tree is superbalanced iteratively 
        
        //dfs to each leaf node and get the depths. save the depths and return false if any depth is more than 2 apart. 
        
        //dfs to each leaf node: 
        //use a stack to visit each node. keep track of node depths by creating a mapping of some sort.
        Deque<NodePair> stack = new ArrayDeque<NodePair>();
        List<Integer> leafDepths = new ArrayList<Integer>();
        
        //add the root node initially. process the node and then add its children left right.
        //processing the node by poping it off the stack and geting the depth. 
        NodePair root = new NodePair(treeRoot, 0);
        stack.push(root);
        
        while(!stack.isEmpty()){
            NodePair nPair = stack.pop();
            BinaryTreeNode n = nPair._Node;
            int depth = nPair._Depth; 
            System.out.print(n.value + " depth:" + depth + " ");
            
            //leaf node 
            if(n.left == null && n.right == null){
            	System.out.println("leaf node found ");
                if(!leafDepths.contains(depth)){
                    leafDepths.add(depth);
                    System.out.println("depth added");
                }
                
                if(leafDepths.size() > 2 || leafDepths.size() == 2 && Math.abs(leafDepths.get(0) - leafDepths.get(1)) >= 2) {
                	System.out.println("returning false condtion met");
                	return false; 
                }
                
            } else {
                if(n.left != null) stack.push(new NodePair(n.left, depth+1));
                if(n.right != null) stack.push(new NodePair(n.right, depth +1)); 
            }
            
            System.out.println("iteration");
            
        }
    
        
        
        
        //save the depth of every leaf node 
                //add it to a list if the list doesnot contain the value. more than 2 distinct leaf node depths and the tree isn't balanced
                //if the difference between the 2 depths is more than 2, return false
        
        System.out.println("\ntrue");
        return true;
    }


    //TO DO: 
    //think of what to do if you have 3 different depths. in what scenario would that be possible? 3,4,5

    
    
    public static int travTest(BinaryTreeNode node) {
    	if(node == null) return 0;
    	
    	
    	int left = travTest(node.left);
    	int right = travTest(node.right);

    	return node.value + left+right;
    }



//TODO: 
    //how did you approach this problem before reading the solution. where specifically did you get stuck? 













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
        System.out.println(travTest(root)); 
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