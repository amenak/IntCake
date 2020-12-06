package interviewcake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class graphColoring {

    public static class GraphNode {

        private String label;
        private Set<GraphNode> neighbors;
        private Optional<String> color;

        public GraphNode(String label) {
            this.label = label;
            neighbors = new HashSet<GraphNode>();
            color = Optional.empty();
        }

        public String getLabel() {
            return label;
        }

        public Set<GraphNode> getNeighbors() {
            return Collections.unmodifiableSet(neighbors);
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        public boolean hasColor() {
            return color.isPresent();
        }

        public String getColor() {
            return color.get();
        }

        public void setColor(String color) {
            this.color = Optional.ofNullable(color);
        }
    }

    //BRUTEFORCE
    public static void colorGraph(GraphNode[] graph, String[] colors) {

        // create a valid coloring for the graph
        if(graph == null) throw new IllegalArgumentException("Graph is empty or null"); 
        if(graph.length < 2) throw new IllegalArgumentException("Graph needs more than 1 node");
        
    	int maxDegree = 0;
    	
    	//find maximum degree
    	for(GraphNode node: graph) {
    		int degree = node.neighbors.size();
    		if(maxDegree < degree)
    			maxDegree = degree;    		
    	}
    	
    	//System.out.println(maxDegree + 1);
    	
    	//from the colors passed in use only those colors 
    	List <String> availableColors = new ArrayList<String>(); 
    	for(int i=0; i < (maxDegree+1); i++) availableColors.add(colors[i]);
    	
    	//loop through each node in the graph. loop through its sets of neighbors. init a list of availble color, poping colors not available, assign one of the colors to the node. 
    	for(GraphNode node: graph) {
    		//System.out.println(node.label + " node parent ");
    		List<String> allowedColors = new ArrayList<String>(availableColors);
    		 
    		
    		
	    		Iterator<GraphNode> neighbor = node.getNeighbors().iterator();
	    		while(neighbor.hasNext()) {
	    			
	    			GraphNode n = neighbor.next();
	    			
	    			//System.out.println("neightbor :" + n.getLabel());
	    			String colorOfNeighbor = null;
	    			if(n.hasColor())
	    			{
	    				colorOfNeighbor = n.getColor(); 
	    			}// its throwing an exception of "no value present here and terminates early"
	    			
	    			if(colorOfNeighbor != null && allowedColors.contains(colorOfNeighbor)) 
	    			{
	    				allowedColors.remove(colorOfNeighbor);
	    			}
	    		} //this while loop skips c!
    		
    		
    		//System.out.println(allowedColors.size() + " colors allowed for node"); //it never reaches this code 
    		node.setColor(allowedColors.get(0)); //assign the remaining color. 
    		//System.out.println(node.getColor() + " node set to this color");
    		
    	}
        
       


    }




/*MISTAKES
 * I did not check if the neighbor had a color or not before removing it from the list. Its an optional object but you must check if null
 * Driving myself crazy because of lack of understanding of iterators! it.next() should only be used once! don't use it as a way to access the object in the code. 
  */  













    // tests

    @Test
    public void lineGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void separateGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        nodeA.addNeighbor(nodeB);
        nodeB.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeD);
        nodeD.addNeighbor(nodeC);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void triangleGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test
    public void envelopeGraphTest() {
    	System.out.println("envelopeGraphTest");
        final GraphNode nodeA = new GraphNode("A");
        final GraphNode nodeB = new GraphNode("B");
        final GraphNode nodeC = new GraphNode("C");
        final GraphNode nodeD = new GraphNode("D");
        final GraphNode nodeE = new GraphNode("E");
        nodeA.addNeighbor(nodeB);
        nodeA.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeA);
        nodeB.addNeighbor(nodeC);
        nodeB.addNeighbor(nodeD);
        nodeB.addNeighbor(nodeE);
        nodeC.addNeighbor(nodeA);
        nodeC.addNeighbor(nodeB);
        nodeC.addNeighbor(nodeD);
        nodeC.addNeighbor(nodeE);
        nodeD.addNeighbor(nodeB);
        nodeD.addNeighbor(nodeC);
        nodeD.addNeighbor(nodeE);
        nodeE.addNeighbor(nodeB);
        nodeE.addNeighbor(nodeC);
        nodeE.addNeighbor(nodeD);
        final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD, nodeE};
        colorGraph(graph, getColors());
        validateGraphColoring(graph);
    }

    @Test(expected = Exception.class)
    public void loopGraphTest() {
        final GraphNode nodeA = new GraphNode("A");
        nodeA.addNeighbor(nodeA);
        final GraphNode[] graph = new GraphNode[] {nodeA};
        colorGraph(graph, getColors());
    }

    private static String[] getColors() {
        return new String[] {"red", "green", "blue", "orange", "yellow", "white"};
    }

    private static void validateGraphColoring(GraphNode[] graph) {

        for (final GraphNode node : graph) {
            if (!node.hasColor()) {
                fail(String.format("Found non-colored node %s", node.getLabel()));
            }
        }

        int maxDegree = 0;
        final Set<String> usedColors = new HashSet<>();

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            maxDegree = Math.max(maxDegree, neighbors.size());
            usedColors.add(node.getColor());
        }

        final int allowedColorCount = maxDegree + 1;

        if (usedColors.size() > allowedColorCount) {
            fail(String.format("Too many colors: %d allowed, but %d actually used",
                               allowedColorCount, usedColors.size()));
        }

        for (final GraphNode node : graph) {
            final Set<GraphNode> neighbors = node.getNeighbors();
            for (final GraphNode neighbor: neighbors) {
                if (neighbor.getColor().equals(node.getColor())) {
                    fail(String.format("Neighbor nodes %s and %s have the same color %s",
                                       node.getLabel(), neighbor.getLabel(), node.getColor()));
                }
            }
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(graphColoring.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}