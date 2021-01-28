package LinkedList;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class DeleteNode {

    public static class LinkedListNode {

        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value) {
            this.value = value;
        }
    }

    //no reference to head node, in place technique
    public static void deleteNode(LinkedListNode nodeToDelete) {

        // delete the input node from the linked list
    	if(nodeToDelete == null) return;
    	
    	if(nodeToDelete.next == null) {
    		nodeToDelete = null; //dead node. but you can't set value to null, so i set the entire node to null.  
    	}
    	
    	nodeToDelete.value = nodeToDelete.next.value;
    	nodeToDelete.next = nodeToDelete.next.next;
    	
    	
    }

    /*O(1) TIME 
     * O(1) SPACE
     */

















    // tests

    @Test
    public void nodeAtBeginningTest() {
        LinkedListNode head = new LinkedListNode(1);
        appendToList(head, 2);
        appendToList(head, 3);
        appendToList(head, 4);

        deleteNode(head);

        LinkedListNode node = head;
        assertEquals(2, node.value);

        node = node.next;
        assertEquals(3, node.value);

        node = node.next;
        assertEquals(4, node.value);

        assertNull(node.next);
    }

    @Test
    public void nodeInTheMiddleTest() {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode nodeToDelete = appendToList(head, 2);
        appendToList(head, 3);
        appendToList(head, 4);

        deleteNode(nodeToDelete);

        LinkedListNode node = head;
        assertEquals(1, node.value);

        node = node.next;
        assertEquals(3, node.value);

        node = node.next;
        assertEquals(4, node.value);

        assertNull(node.next);
    }

    @Test(expected = Exception.class)
    public void nodeAtTheEndTest() {
        LinkedListNode head = new LinkedListNode(1);
        appendToList(head, 2);
        appendToList(head, 3);
        LinkedListNode nodeToDelete = appendToList(head, 4);

        deleteNode(nodeToDelete);
    }

    @Test(expected = Exception.class)
    public void oneNodeListTest() {
        LinkedListNode head = new LinkedListNode(1);
        deleteNode(head);
    }

    private static LinkedListNode appendToList(final LinkedListNode head, int value) {
        LinkedListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new LinkedListNode(value);
        return current.next;
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DeleteNode.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
