import java.util.ArrayList;

/**
 * @author Riley Sutton on 2/05/18
 * @project Comp2511 Ass2
 */
public class ZeroHeuristic<T> implements Heuristic<T>{

    @Override
    public int calculate(Pair<Pair<T, T>, ArrayList<Pair<T, T>>> sp, ArrayList<Pair<T, T>> currshipments, Graph<T> graph) {
        // This will obviously run at O(1) time complexity
        return 0;
    }
}
