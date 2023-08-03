import javafx.util.Pair;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class PageRank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PageRank
{
    private static double dampingFactor = .85;
    private static int iter = 10;
    /**
     * build the fromLinks and toLinks 
     */
    
    public static void computeLinks(Graph graph){
        
        for(Edge links : graph.getOriginalEdges()){ //iterate through each edge
            links.toCity().addFromLinks(links.fromCity()); //add fromCity to toCity
            links.fromCity().addToLinks(links.toCity()); //add toCity to fromCity
        }

        //printPageRankGraphData(graph);  ////may help in debugging
    }

    public static void printPageRankGraphData(Graph graph){
        System.out.println("\nPage Rank Graph");

        for (City city : graph.getCities().values()){
            System.out.print("\nCity: "+city.toString());
            //for each city display the in edges 
            System.out.print("\nIn links to cities:");
            for(City c:city.getFromLinks()){

                System.out.print("["+c.getId()+"] ");
            }

            System.out.print("\nOut links to cities:");
            //for each city display the out edges 
            for(City c: city.getToLinks()){
                System.out.print("["+c.getId()+"] ");
            }
            System.out.println();;

        }    
        System.out.println("=================");
    }
    
    public static void computePageRank(Graph graph){
        computeLinks(graph);

        Map<City,Double>rank = new TreeMap<>(); //store rank values
        Map<City, City> lostRanking = new HashMap<>(); //neighbour lost most ranking
        int nNodes = graph.getCities().size(); //total number of nodes
        int c = 1;
        
        for(City n : graph.getCities().values()){rank.put(n, 1.0/nNodes);} //initialize rank values

        while(c < iter){ //iterate 
            for(City n : graph.getCities().values()){ //iterate through each city
                double nRank = 0;

                //Challenge
                //neighbour with maximum impact
                double nInflunce = 0; //initialize max neighbor influence
                City nImpact = null; //initialize neighbour with max impact

                for(City backNeighbour : n.getFromLinks()){ //iterate through backward neibours
                    //calculate share of ranking from each neighbour
                    double neiShare = (rank.get(backNeighbour))/(backNeighbour.getToLinks().size());
                    nRank += neiShare;

                    //Challenge
                    // initiate neighbourInfluence
                    double neighborInfluence = rank.get(n) - (rank.get(backNeighbour) - neiShare);
                    if (neighborInfluence > nInflunce) { //check current neighbour has greater impact
                        nInflunce = neighborInfluence; //update max influence
                        nImpact = backNeighbour; //update imact
                    } 
                }

                nRank = ((1-dampingFactor)/(nNodes))+dampingFactor*nRank; //calculate updated rank value for city
                rank.put(n,nRank);

                //Challenge
                // update neighbour with most impact
                lostRanking.put(n, nImpact);
            } 
            c++;  
        }
        
        
        System.out.println("Iteration" + iter + ":\n");
        for(Map.Entry<City,Double>r : rank.entrySet()){
            System.out.println(r.getKey().getName() + "[" + r.getKey().getId() + "]: " + r.getValue());
        }



        //PRINT CHALLENGE PAGERANK METHOD RANKING IN-NEIGHBOURS
        System.out.println("\n=================");
        System.out.println("Print Challenge");
        System.out.println("Nothing to test with so here we go");
        System.out.println("Page rank algorithm derives a node's ranking from"); 
        System.out.println("the ranking of its in-neighbors, which are in"); 
        System.out.println("turn influenced by the ranking of their in-neighbors\n");
        
        for (City n : graph.getCities().values()) {
            City maxN = lostRanking.get(n);
            String nodeID = (maxN != null) ? maxN.getName() : "No Node";
            //for(int i = 0; i < sortedKeys.size(); i++){
                //City key = sortedKeys.get(i);
                //System.out.println(n.getName() + "[" + i + "] : " + nID);
            //}
            System.out.println("Node " + n.getName() + ": " + nodeID);
        }
        System.out.println("=================");


        System.out.println("\n=================");
        System.out.println("Print GRAPHDATA\n");
        printPageRankGraphData(graph);

    }
    
}

