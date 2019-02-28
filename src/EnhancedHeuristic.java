import java.util.ArrayList;

/**
 * @author Riley Sutton on 2/05/18
 * @project Comp2511 Ass2
 */
public class EnhancedHeuristic<T> implements Heuristic<T> {
    @Override
    public int calculate(Pair<Pair<T,T>,ArrayList<Pair<T,T>>> sp, ArrayList<Pair<T,T>> currshipments, Graph<T> graph) {
        // As can be seen below with two procedural operations adding to the overall sum, both being O(n), we can conclude that the time complexity of the overall heuristic is O(n)

        // Initialise our sum
        int sum = 0;

        // Linearly traverse through our currshipments list and add the cost of travelling through each of the shipments, O(n)
        for (Pair<T,T> p : currshipments){
            sum += graph.getEdge(p.getT1(),p.getT2()).getCost(); // O(1) for a Adjacency Matrix
        }

        // Linearly traverse all the shipments in currshipments and find the distance from the current position to the nearest shipment, O(n)
        int smallestdist = Integer.MAX_VALUE;
        boolean smallestdistinit = false;
        for (Pair<T,T> p : currshipments){
            if (sp.getT1().getT2().equals(p.getT1())){
                smallestdist = 0;
            }
            else{
                if ( graph.getEdge(sp.getT1().getT2(),p.getT1()).getCost() < smallestdist){
                    smallestdist = graph.getEdge(sp.getT1().getT2(), p.getT1()).getCost(); // O(1) for an adjacency matrix
                }
            }
            smallestdistinit = true;
        }
        if (!smallestdistinit) smallestdist = 0;
        sum += smallestdist; //Add this shortest distance

        return sum;
    }
}
