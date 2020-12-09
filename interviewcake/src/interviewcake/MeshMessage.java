package interviewcake;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MeshMessage {

    public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) {
    	if(graph == null || startNode == null || endNode == null) throw new IllegalArgumentException("parameters can not be null");
    	if(!graph.containsKey(endNode)) throw new IllegalArgumentException("End Node does not exist in the graph");
    	//BFS for shortest path 
    	
    	Deque<String> q = new ArrayDeque<String>(); 
        Map<String, String> paths = new HashMap<String,String>(); //how do you declare a map in java? 
        
        paths.put(startNode, null);
    	q.add(startNode);
        
        while(!q.isEmpty()) { //infinite loop q is never empty? wrong add to path map
        	String node = q.pop();

        	if(node.equals(endNode)) { //can i do == with two strings? No
        		break; 
        	}
        	
        	String [] nodeNeighbors = graph.get(node);
        	for(int i=0; i < nodeNeighbors.length; i++) {
        		String neighbor = nodeNeighbors[i];
        		if(!paths.containsKey(neighbor)) { //how do we check if something exists in a map - containsKey
        			paths.put(neighbor, node);
        			q.add(neighbor);
        		}
        		//add to pathmap - how do you add to a map -put
        		
        	}        	
        	
        }
        
        
        //return the path
        //how do you declare a list - had wrong library
        List<String> finalPath = new ArrayList<String>(); 
        String current = endNode;
        //we start with the end node in the map. we keep looping until we reach the start node. 
       
        while(current != null) {
        	finalPath.add(current);
        	if(!paths.containsKey(current)) {
        		return null; //path does not exist
        	}
        	current = paths.get(current);
        }
        
        Collections.reverse(finalPath);
        
        //how do i return a list as a string array. pass it as a param
        
        return finalPath.toArray(new String[finalPath.size()]); 
    }



/*Lessons Learned
 * BFS will give you the shortest path
 * Don't forget to mark the seen nodes
 * Use a map to keep track of paths 
 * HashMaps or arrays can be used to store graphs. they don't need to be stored in a traditional GraphNode class.
 * */













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
        Result result = JUnitCore.runClasses(MeshMessage.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
