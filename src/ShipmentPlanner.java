import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The type Shipment planner.
 *
 * @author Riley Sutton on 19/04/18
 * @project Comp2511 Ass2
 */

// Comments regarding performance of heuristics are available in the respective Java files
// ZeroHeuristic: O(1)
// EnhancedHeuristic: O(n)
public class ShipmentPlanner {

    private HashMap<String,Integer> refuellingtimes;
    private ArrayList<Pair<String,String>> shipments;
    private HashMap<Pair<String,String>,Integer> traveltimes;

    private Printer<String> printer;
    private Graph<String> graph;

    /**
     * Instantiates a new Shipment planner.
     */
    public ShipmentPlanner(){
        refuellingtimes = new HashMap<>();
        shipments = new ArrayList<>();
        traveltimes = new HashMap<>();
        printer = new Printer<>();
        graph = new AMGraph<>();
    }

    /**
     * Main class which bootstraps the ShipmentPlanner class.
     *
     * @param args the arguments containing the input file
     * @throws FileNotFoundException if the input file is not found
     */
    public static void main(String args[]) throws FileNotFoundException {
        ShipmentPlanner sp = new ShipmentPlanner();
        Scanner sc = new Scanner(new File(args[0]));
        CommandParser cp = new CommandParser();

        // Scan each line of our file and store the data accordingly
        while(sc.hasNextLine()){
            Command c = cp.parseCommand(sc.nextLine());
            sp.runCommand(c);
        }

        // Close our file since we are done with it.
        sc.close();

        // Create the graph with the data we have
        sp.createGraph();

        /* Find our path with cost and nodes expanded and receive it in this format:
        *  Path = [ List of nodes, [Final Cost, Nodes Expanded] ]*/
        Pair<ArrayList<Node<String>>,Pair<Integer,Integer>> path = sp.findPath();

        // Print our solution using the printer with the data received before
        sp.printer.print(path);
    }

    /**
     * Find the most optimum path for our shipments.
     *
     * @return the path
     */
    public Pair<ArrayList<Node<String>>,Pair<Integer,Integer>> findPath(){
        // Instantiate our PathFinder with our EnhancedHeuristic
        AStarShipmentPathFinder<String> spf = new AStarShipmentPathFinder<String>(graph,shipments, new EnhancedHeuristic<>());

        // Find the most optimal path using our heuristic and starting from the node matching "Sydney"
        Pair<ArrayList<Node<String>>,Pair<Integer,Integer>> path = spf.findPath(graph.getNode("Sydney"));

        // Return this path
        return path;
    }

    /**
     * Run a command which has been parsed from the input file.
     *
     * @param c the command parsed.
     */
    public void runCommand(Command c){
        // Figure out what type of input we received
        if (c instanceof RefuellingCommand){
            // Refuelling time has been entered and we will add the data to our list accordingly
            refuellingtimes.put(((RefuellingCommand) c).getName(), ((RefuellingCommand) c).getTime());
        } else if (c instanceof ShipmentCommand){
            // A Shipment has been entered and we will add this to our shipment list.
            shipments.add(new Pair<>(((ShipmentCommand) c).getName1(), ((ShipmentCommand) c).getName2()));
        } else if (c instanceof TimeCommand){
            /* A time for travelling between two points has been entered
             * We can assume the travel time is symmetrical as per the spec*/
            traveltimes.put(new Pair<>(((TimeCommand) c).getName1(), ((TimeCommand) c).getName2()),((TimeCommand) c).getTime());
            traveltimes.put(new Pair<>(((TimeCommand) c).getName2(), ((TimeCommand) c).getName1()),((TimeCommand) c).getTime());
        } else{
            // The command entered wasn't recognised and therefore we can simply ignore it
        }
    }

    /**
     * Create a graph from data we have collected from the input file.
     */
    public void createGraph(){
        // Go through all of our refuelling times to add nodes to our graph
        for (String s : refuellingtimes.keySet()){
            graph.addValue(s);
        }

        /* Add the corresponding travel times for each of the travel times we gathered before
        *  Also add the refuel times for the starting nodes*/
        for (Pair<String,String> sp : traveltimes.keySet()){
            graph.addEdge(sp.getT1(),sp.getT2(),refuellingtimes.get(sp.getT1()) + traveltimes.get(sp),true);
        }
    }
}
