import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

// //=============================================================================
// //   
// //   Finds and returns a collection of all the articulation points in the undirected
// //   graph, without walking edges
// //=============================================================================

public class ArticulationPoints{

    // Use the algorithm from the lectures, but you will need a loop to check through
    // all the Stops in the graph to find any Stops which were not connected to the
    // previous Stops, and apply the lecture slide algorithm starting at each such stop.

    public static Collection<Stop> findArticulationPoints(Graph graph) {
        System.out.println("calling findArticulationPoints");
        graph.computeNeighbours();   // To ensure that all stops have a set of (undirected) neighbour stops
        
        Set<Stop> articulationPoints = new HashSet<>();
        Map<Stop, Integer> depths = new HashMap<>();
        int numSubtrees = 0;
        //int numSubtrees = 1;
        //int numSubtrees = -2;
        
        for (Stop stop : graph.getStops()) {  // Loop all graph stops
            
            if (!depths.containsKey(stop)) {
                depths.put(stop, 0); // depth starting node = 0
                int subtreeCount = 0;
                
                for (Stop n : stop.getNeighbours()) {  // visit starting node neighbours
                    
                    if (!depths.containsKey(n)) {   // visit neighbour - update depth, articulation
                        recArtPts(n, 1, stop, depths, articulationPoints, numSubtrees);
                        
                        subtreeCount++; // for checking articulation
                        numSubtrees++; // for recArtPts
                        
                        
                        if(depths.get(stop)==0){
                            if (subtreeCount > 1) {  // starting node is articulation point
                                articulationPoints.add(stop);
                            }
                        }  
                        
                        else { 
                            if(depths.get(n) >= depths.get(stop)){
                                articulationPoints.add(stop);
                            }
                        }
                    }
                }
            }
        }
        
        return articulationPoints;
    }
    
    private static int recArtPts(Stop node, int depth, Stop fromNode, Map<Stop, Integer> depths, Set<Stop> articulationPoints, int numSubtrees) {
        depths.put(node, depth); // Set depth current node
        int rB = depth; // rB to current depth
    
        for (Stop neighbour : node.getNeighbours()) { // visit starting node neighbours
            
            if (!depths.containsKey(neighbour)) {
                int cR = recArtPts(neighbour, depth + 1, node, depths, articulationPoints, numSubtrees); // visit neighbour - update depth, articulation
                rB = Math.min(cR, rB); // up.rB from the reach of the neighbour
                
                if (cR >= depth && fromNode != null) { // up.subtree at the neighbour wont reach beyond current
                    articulationPoints.add(node);
                }
            } 
            
            else if (!neighbour.equals(fromNode)) {
                rB = Math.min(depths.get(neighbour), rB);  // up.rB - depth - visited neighbour
            }
        }
        
        return rB;
    }

}