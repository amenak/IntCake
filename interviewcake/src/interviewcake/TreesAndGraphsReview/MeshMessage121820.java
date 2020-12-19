package interviewcake.TreesAndGraphsReview;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MeshMessage121820 {
	
	public static String [] constructPath(String startNode, String endNode, Map<String,String> nodesMap) {
		
		List<String> path = new ArrayList<String>();
		
		String current = endNode; 
		while(current != null) {
			path.add(current);
			current = nodesMap.get(current);
		}
		
		Collections.reverse(path);
		return path.toArray(new String [path.size()]); 
	}
	
    public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) {
    	if(startNode.equals(endNode)) return new String [] {startNode};
    	if(!graph.containsKey(endNode)) throw new IllegalArgumentException("end node does not exist");
    	
        Queue<String> q = new ArrayDeque<>(); //LinkedList is easy O(1) pop. not sure if arraydeque is the same.. 
        Map<String, String> nodeMap = new HashMap<>(); //how do you delcare a map? i hesitated
        
        q.add(startNode);
        nodeMap.put(startNode, null);
        
       int iteration = 0;
        while(!q.isEmpty()) {
        	iteration = iteration + 1;
        	String node = q.remove(); //could i pop from deque but not from queue? cool
        	
        	/*if(node.equals(endNode)) { //more iterations no? if you put it here instead of the loop. YES if u put it in for loop its less iterations.
        		//basically you have to wait until you reach adam to break. if in the for loop  you can break when u reach amelia. 
        			//break; // breaks for loop or while loop?
        		return constructPath(startNode, endNode, nodeMap); 
        	}*/
        	

        	
        	for(String neighbor : graph.get(node)) {
        		if(!nodeMap.containsKey(neighbor)) {
        			q.add(neighbor);
        			nodeMap.put(neighbor, node);
        		}
        		
        	if(node.equals(endNode)) { //more iterations no? if you put it here instead of the loop. YES if u put it in for loop its less iterations.
        		//basically you have to wait until you reach adam to break. if in the for loop  you can break when u reach amelia. 
        			//break; // breaks for loop or while loop?
        		
        		return constructPath(startNode, endNode, nodeMap); 
        	}
        		
        		
        	}
        	
        	
        }
        
        return null;

    }



    // tests

    @Test
    public void twoHopPath1Test() {
        final String[] expected = {"a", "c", "e"};
        final String[] actual = getPath(getNetwork(), "a", "e");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoHopPath2Test() {
        final String[] expected = {"d", "a", "c"};
        final String[] actual = getPath(getNetwork(), "d", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath1Test() {
        final String[] expected = {"a", "c"};
        final String[] actual = getPath(getNetwork(), "a", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath2Test() {
        final String[] expected = {"f", "g"};
        final String[] actual = getPath(getNetwork(), "f", "g");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath3Test() {
        final String[] expected = {"g", "f"};
        final String[] actual = getPath(getNetwork(), "g", "f");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void zeroHopPath() {
        final String[] expected = {"a"};
        final String[] actual = getPath(getNetwork(), "a", "a");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noPathTest() {
        final String[] actual = getPath(getNetwork(), "a", "f");
        assertNull(actual);
    }

    @Test(expected = Exception.class)
    public void startNodeNotPresentTest() {
        getPath(getNetwork(), "h", "a");
    }

    @Test(expected = Exception.class)
    public void endNodeNotPresentTest() {
        getPath(getNetwork(), "a", "h");
    }

    private static Map<String, String[]> getNetwork() {
        return new HashMap<String, String[]>() { {
            put("a", new String[] {"b", "c", "d"});
            put("b", new String[] {"a", "d"});
            put("c", new String[] {"a", "e"});
            put("d", new String[] {"a", "b"});
            put("e", new String[] {"c"});
            put("f", new String[] {"g"});
            put("g", new String[] {"f"});
        }};
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MeshMessage121820.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}