import java.util.*;

/**
 * The type A* path finder.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 22/04/18
 * @project Comp2511 Ass2
 */
public class AStarShipmentPathFinder<T> {

    private Graph<T> graph;
    private ArrayList<Pair<T,T>> shipments;
    private Heuristic<T> heuristic;

    /**
     * Instantiates a new A* PathFinder.
     *
     * @param graph     the graph
     * @param shipments the shipments
     * @param heuristic the heuristic
     */
    public AStarShipmentPathFinder(Graph<T> graph, ArrayList<Pair<T,T>> shipments, Heuristic<T> heuristic){
        this.graph = graph;
        this.shipments = shipments;
        this.heuristic = heuristic;
    }

    private Pair<Pair<T,T>,ArrayList<Pair<T,T>>> lowestPair(ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> open, HashMap<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>,Integer> fscore){
        // Finds the lowest pair of nodes a shipment has to be made on within the open list, to determine which path will be expanded next.

        // Initiate a new variable to return as the lowest pair and a huge score to deviate from
        Pair<Pair<T,T>,ArrayList<Pair<T,T>>> lowestnp = null;
        int lowestscore = Integer.MAX_VALUE;

        // Search linearly for the lowest scoring pair
        for (Pair<Pair<T,T>,ArrayList<Pair<T,T>>> p : open){
            if (fscore.get(p) < lowestscore){
                lowestnp = p;
                lowestscore = fscore.get(p);
            }
        }
        return lowestnp;
    }

    private ArrayList<Pair<T,T>> shipmentsNotCovered(ArrayList<Pair<T,T>> path){
        // Generate a list of shipments that arent present on the list provided

        // Linearly remove every element on path, if it is present then it will be removed from the final list returned
        ArrayList<Pair<T,T>> currshipments = new ArrayList<>(shipments);
        for (Pair<T,T> p : path){
            currshipments.remove(p);
        }
        return currshipments;
    }

    private ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> getShipmentPairList(ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> pl, Pair<Pair<T,T>,ArrayList<Pair<T,T>>> spnew){
        // Generate a list of pairs that are equal according to equalsState, with the provided list pl of pairs

        // Linearly add every element that is equal according to equalsState, to splist to return
        ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> splist = new ArrayList<>();
        for (Pair<Pair<T,T>,ArrayList<Pair<T,T>>> p : pl){
            if (equalsState(p,spnew)) splist.add(p);
        }
        return splist;
    }

    private boolean equalsState(Pair<Pair<T,T>,ArrayList<Pair<T,T>>> fl, Pair<Pair<T,T>,ArrayList<Pair<T,T>>> sl){
        // Return that the states are equal if fl is a subset of sl, sl is a subset of fl, and they are both currently on the same element
        // Repetitions of the same element are simply counted as if there were only one
        return fl.getT2().containsAll(sl.getT2()) && sl.getT2().containsAll(fl.getT2()) && sl.getT1().equals(fl.getT1());
    }

    private ArrayList<Node<T>> shipmentsToNodes(ArrayList<Pair<T,T>> spath){
        // Quick method to turn a path of shipments into a path of nodes which we can use in other classes

        // Initiate an empty node path and fill it with the corresponding nodes
        // Similarly check whether the path we got was empty, in which case just return our empty list
        ArrayList<Node<T>> npath = new ArrayList<>();
        if (spath.isEmpty()) return npath;

        // Add the first node so our future conditions will work, it will not affect the first if statement since it will always fail after this statement
        npath.add(graph.getNode(spath.get(0).getT1()));
        for (Pair<T,T> p : spath){
            if (!npath.get(npath.size() - 1).getValue().equals(p.getT1())){ // If the last node we added is not a repetition
                npath.add(graph.getNode(p.getT1()));
            }
            if (!npath.get(npath.size() - 1).getValue().equals(p.getT2())){ // If the last node we added is not a repetition
                npath.add(graph.getNode(p.getT2()));
            }
        }
        return npath;
    }

    /**
     * Find the most optimum path using the A* search.
     *
     * @param from the start node
     * @return the node path, cost, and nodes expanded
     */
    public Pair<ArrayList<Node<T>>,Pair<Integer,Integer>> findPath(Node<T> from) {

        // Init the expanded nodes counter
        int expanded = 0;

        // Init the frompath for our first pair
        ArrayList<Pair<T,T>> frompath = new ArrayList<>();
        frompath.add(new Pair<>(from.getValue(), from.getValue()));

        // Init both open and closed lists, to where in open we add our first pair (From our start node)
        ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> closed = new ArrayList<>();
        ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> open = new ArrayList<>();
        // Add a bogus start pair from our start node to our start node (0 cost) which bootstraps the A* search
        open.add(new Pair<>(new Pair<>(from.getValue(), from.getValue()), frompath));

        // Init our gscore hashmap which equates a specific pair to a current cost
        HashMap<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>,Integer> gscore = new HashMap<>();
        // Bootstrap the gscore with our 0 cost initial pair
        gscore.put(new Pair<>(new Pair<>(from.getValue(), from.getValue()), frompath),0);

        // Init our fscore hashmap which similarly equates a specific pair to a current cost + a heuristic cost
        HashMap<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>,Integer> fscore = new HashMap<>();
        // Bootstrap the fscore with our 0 cost initial pair plus whatever the heuristic is for this specific pair
        fscore.put(new Pair<>(new Pair<>(from.getValue(), from.getValue()), frompath),heuristic.calculate(new Pair<>(new Pair<>(from.getValue(), from.getValue()), new ArrayList<>()),shipments,graph));

        // Begin the initial loop for the A* search
        while(!open.isEmpty()){

            // Find the lowest fscore pair in open and remove it from open then add it to closed
            Pair<Pair<T,T>,ArrayList<Pair<T,T>>> curr = lowestPair(open,fscore);
            open.remove(curr);
            closed.add(curr);

            // Increment expanded nodes after this operation as per the spec
            expanded++;

            // Check whether this is a pair which completes all required shipments
            // Since our heuristic is admissible and A* is correct, the first time this is run must be the most optimum order of shipments
            if (shipmentsNotCovered(curr.getT2()).isEmpty()){

                // Add our expanded nodes and cost to our final returned data
                Pair<ArrayList<Node<T>>,Pair<Integer,Integer>> retp = new Pair<>();
                retp.setT1(shipmentsToNodes(curr.getT2()));
                retp.setT2(new Pair<>(fscore.get(curr), expanded));
                return retp;
            }

            // Begin to find the neighbours of the final shipment in the pair
            for (Pair<T,T> p : shipments){

                // Create our new pair with the provided shipment p
                Pair<Pair<T,T>,ArrayList<Pair<T,T>>> spnew = new Pair<>();
                spnew.setT1(p);
                ArrayList<Pair<T,T>> spnewpath = new ArrayList<>(curr.getT2());
                spnewpath.add(p);
                spnew.setT2(spnewpath);

                // Calculate the corresponding gscore with this added shipment
                // If the last node in our path is the same as the first node in the new shipment then the cost is 0 + the cost to traverse the new shipment
                // Otherwise it is the cost to get from the last node on our path to the first node in the new shipment + the cost to traverse the new shipment
                if (curr.getT1().getT2().equals(p.getT1())){
                    gscore.put(spnew,gscore.get(curr) + graph.getEdge(p.getT1(),p.getT2()).getCost());
                }
                else {
                    gscore.put(spnew, gscore.get(curr) + graph.getEdge(curr.getT1().getT2(), p.getT1()).getCost() + graph.getEdge(p.getT1(),p.getT2()).getCost());
                }
                // The new fscore is calculated as the new gscore + our heuristic applied to the new pair
                fscore.put(spnew, gscore.get(spnew) + heuristic.calculate(spnew,shipmentsNotCovered(spnew.getT2()),graph));

                // Find the matching state pairs in both the open and closed lists
                ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> spclosed = getShipmentPairList(closed,spnew);
                ArrayList<Pair<Pair<T,T>,ArrayList<Pair<T,T>>>> spopen = getShipmentPairList(open,spnew);

                // If there are elements in closed of matching state, where our pair is more optimal, then remove the other pair from closed and add our new pair to open
                if (!spclosed.isEmpty()){
                    for (Pair<Pair<T, T>, ArrayList<Pair<T, T>>> aSpclosed : spclosed) {
                        if (fscore.get(spnew) <= fscore.get(aSpclosed)) {
                            closed.remove(aSpclosed);
                            open.add(spnew);
                        }
                    }
                }
                // Otherwise if there are elements in open of matching state, where our pair is more optimal, remove the other pair from open and add the new pair to open
                else if (!spopen.isEmpty()) {
                    for (Pair<Pair<T, T>, ArrayList<Pair<T, T>>> aSpopen : spopen) {
                        if (fscore.get(spnew) <= fscore.get(aSpopen)) {
                            open.remove(aSpopen);
                            open.add(spnew);
                        }
                    }
                }
                // Otherwise we have never seen this pair before and it should be expanded further
                else{
                    open.add(spnew);
                }
            }
        }

        // Something went wrong and open is empty!
        System.out.println("FAILED");
        return null;
    }

    /**
     * Find the most optimum path using the A* search.
     *
     * @param from the start node
     * @return the node path, cost, and nodes expanded
     */
    public Pair<ArrayList<Node<T>>,Pair<Integer,Integer>> findPath(T from) {
        return findPath(graph.getNode(from));
    }
}
