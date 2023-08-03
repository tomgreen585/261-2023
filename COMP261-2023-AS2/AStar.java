/**
 * Implements the A* search algorithm to find the shortest path
 * in a graph between a start node and a goal node.
 * It returns a Path consisting of a list of Edges that will
 * connect the start node to the goal node.
 */

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    
    
    private static String timeOrDistance = "distance";    // way of calculating cost: "time" or "distance"

    
    public static List<Edge> findShortestPath(Stop start, Stop goal, String type) { // find the shortest path between two stops
        if (start == null || goal == null) {return null;}
        timeOrDistance = (type.equals("time")) ? "time" : "distance"; // Set the mode of calculating cost
    
        PriorityQueue <PathItem> queue = new PriorityQueue <PathItem> (); // priority queue
        Set <Stop> visited = new HashSet <Stop> (); // visited stops
        Map <Stop, Edge> bP = new HashMap <Stop, Edge> (); // backpointers
        PathItem sItem = new PathItem (start, null, 0.0, heuristic(start, goal));  // start path - add to queue
        
        queue.add(sItem);
    
        while(!queue.isEmpty()){ // shortest path
            PathItem current = queue.poll(); // path item - lowest estimated cost
            
            if(current.getStop().equals(goal)) {  // reached goal
                List <Edge> path = new ArrayList<Edge>(); // reconstruct the path
                Stop stop = goal;
                
                while(!stop.equals(start)) {
                    Edge edge = bP.get(stop);
                    path.add(edge);
                    stop = edge.fromStop();
                }
                
                Collections.reverse(path);
                return path;
            }
    
            if(visited.contains(current.getStop())) { // check if visited stop
                continue;
            }
            
            visited.add(current.getStop());  // stop as visited
    
            for(Edge edge : current.getStop().getOutgoingEdges()) { // outgoing edges from stop
                Stop neighbor = edge.toStop();
                
                if(visited.contains(neighbor)) { // if visited neighbor
                    continue;
                }
    
                double costs = current.getCostSoFar() + edgeCost(edge); //  calc cost - new path
                double estCosts = costs + heuristic(neighbor, goal); // Calc estcosts - new path
    
                PathItem neighborItem = new PathItem(neighbor, null, costs, estCosts); // new path item for neighbor - add queue
                queue.add(neighborItem);
                bP.put(neighbor, edge); // Upd.backpointer - neighbour
            }
        }
        
        return null;
    }

    /** Return the heuristic estimate of the cost to get from a stop to the goal */
    public static double heuristic(Stop current, Stop goal) {
        if (timeOrDistance=="distance"){ return current.distanceTo(goal);}
        else if (timeOrDistance=="time"){return current.distanceTo(goal) / Transport.TRAIN_SPEED_MPS;}
        else {return 0;}
    }

    /** Return the cost of traversing an edge in the graph */
    public static double edgeCost(Edge edge){
        if (timeOrDistance=="distance"){ return edge.distance();}
        else if (timeOrDistance=="time"){return edge.time();}
        else {return 1;}
    }
}
