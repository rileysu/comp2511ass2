import java.util.ArrayList;

/**
 * The interface Heuristic.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 2/05/18
 * @project Comp2511 Ass2
 */
public interface Heuristic<T> {

    /**
     * Calculate the heuristic with the given information.
     *
     * @param sp            the shipment pair
     * @param currshipments the shipments that are still to be done
     * @param graph         the graph we are traversing
     * @return the cost
     */
    int calculate(Pair<Pair<T,T>,ArrayList<Pair<T,T>>> sp, ArrayList<Pair<T,T>> currshipments, Graph<T> graph);

}
