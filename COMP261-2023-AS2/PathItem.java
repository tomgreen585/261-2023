import java.util.List;
/**
 * AStar search (and Dijkstra search) uses a priority queue of partial paths
 * that the search is building.
 * Each partial path needs several pieces of information, to specify
 * the path to that point, its cost so far, and its estimated total cost
 */

 public class PathItem implements Comparable<PathItem> {

    private Stop stop; //current stop
    private List<Edge> pathSoFar; 
    
    private double costSoFar;
    private double estimatedTotalCost;

    //Instance vars in PathItem
    public PathItem(Stop stop, List<Edge> pathSoFar, double costSoFar, double estimatedTotalCost) {
        this.stop = stop;
        this.pathSoFar = pathSoFar;
        this.costSoFar = costSoFar;
        this.estimatedTotalCost = estimatedTotalCost;
    }

    //Get instance vars
    public Stop getStop() {return stop;}
    public List<Edge> getPathSoFar() {return pathSoFar;}
    public double getCostSoFar() {return costSoFar;}
    public double getEstimatedTotalCost() {return estimatedTotalCost;}

    public int compareTo(PathItem other) {
        if (this.estimatedTotalCost < other.estimatedTotalCost) {return -1;} // if this is less than other
        else if (this.estimatedTotalCost > other.estimatedTotalCost) {return 1;} // if this is greater than other
        else {return 0;} // if this is equal to other
    }

}
