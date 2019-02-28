import java.util.ArrayList;

/**
 * The type Printer.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 25/04/18
 * @project Comp2511 Ass2
 */
public class Printer<T> {

    /**
     * Instantiates a new Printer.
     */
    public Printer(){
    }

    /**
     * Print our path and associated cost and expanded nodes, according to the provided spec
     *
     * @param path the path, cost and expanded nodes
     */
    public void print(Pair<ArrayList<Node<T>>,Pair<Integer,Integer>> path){
        System.out.println(path.getT2().getT2() + " nodes expanded");
        System.out.println("cost = " + path.getT2().getT1());
        //Print out all possible adjacent pairs of nodes in the list
        for (int i = 1; i < path.getT1().size(); i++){
            System.out.println("Ship " + path.getT1().get(i-1).getValue() + " to " + path.getT1().get(i).getValue());
        }
    }
}
