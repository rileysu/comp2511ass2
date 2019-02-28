import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * The type Am graph.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 18/04/18
 * @project 2511Project
 */
public class AMGraph<T> implements Graph<T> {

    private HashMap<Pair<Node<T>, Node<T>>, Edge<T>> mat;
    private ArrayList<Node<T>> nodes;
    private ArrayList<Edge<T>> edges;

    /**
     * Instantiates a new Adjacency Matrix Graph.
     */
    public AMGraph() {
        //Init our matrix, nodes and edges
        mat = new HashMap<>();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addNode(Node<T> n) {
        if (nodes.contains(n))
            throw new IllegalArgumentException("Node:" + n.toString() + " already exists in the Graph");

        nodes.add(n);
    }

    @Override
    public Node<T> getNode(T v) {
        // Linear search for the node in the nodes list
        for (Node<T> n : nodes){
            if (n.getValue().equals(v)) return n;
        }
        return null;
    }

    @Override
    public ArrayList<Node<T>> getNodes() {
        return nodes;
    }

    @Override
    public void removeNode(Node<T> n) {

        // Remove node from nodes list
        nodes.remove(n);

        // Remove any edges containing the node in question
        removeEdgesContaining(n);
    }

    @Override
    public Node<T> addValue(T v) {
        Node<T> n = new Node<T>(v);
        addNode(n);
        return n;
    }

    @Override
    public Node<T> getValue(T v) {
        // Added for completeness even though equivalent to getNode
        // Linear search for the node in the nodes list
        for (Node<T> n : nodes){
            if (n.getValue().equals(v)) return n;
        }
        return null;
    }

    @Override
    public void removeValue(T v) {
        // Copy list of nodes and remove any nodes which have the same value to elements in this new list, in our main nodes list
        ArrayList<Node<T>> searchlist = new ArrayList<>(nodes);
        for (Node<T> n : searchlist) {
            if (n.getValue().equals(v)) removeNode(n);
        }
    }

    @Override
    public void addEdge(Node<T> from, Node<T> to, int cost, boolean directional) {
        Edge<T> e = new Edge<>(from, to, cost);
        mat.put(new Pair<>(from, to), e);
        if (!edges.contains(e)) edges.add(e);

        if (!directional && !from.equals(to)) {
            e = new Edge<>(to, from, cost);
            mat.put(new Pair<>(to, from), e);
            if (!edges.contains(e)) edges.add(e);
        }
    }

    @Override
    public void addEdge(T from, T to, int cost, boolean directional) {
        addEdge(getValue(from),getValue(to),cost,directional);
    }

    @Override
    public Edge<T> getEdge(Node<T> from, Node<T> to) {
        for (Edge<T> e : edges){
            if (e.getFrom().equals(from) && e.getTo().equals(to)) return e;
        }
        return null;
    }

    @Override
    public Edge<T> getEdge(T from, T to) {
        return getEdge(getNode(from),getNode(to));
    }

    @Override
    public ArrayList<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public ArrayList<Edge<T>> getEdgesFrom(Node<T> n) {
        // Make a list of edges which match this condition by linearly searching edges list
        ArrayList<Edge<T>> ret = new ArrayList<>();
        for (Edge<T> e : edges) {
            if (e.getFrom().equals(n)) {
                ret.add(e);
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Edge<T>> getEdgesFrom(T n) {
        return getEdgesFrom(getValue(n));
    }

    @Override
    public void removeEdge(Node<T> from, Node<T> to) {
        // Remove the edge if the specific edge exists in either the edges list or the matrix hasmap
        edges.removeIf(x -> x.getFrom().equals(from) && x.getTo().equals(to));
        mat.remove(new Pair<>(from, to));
    }

    @Override
    public void removeEdge(T from, T to) {
        removeEdge(getValue(from),getValue(to));
    }

    @Override
    public void removeEdgesContaining(Node<T> n) {
        // Search for any edges containing the node, in the matrix and edges list and remove them
        for (Edge<T> e : edges) {
            if (e.getFrom().equals(n) || e.getTo().equals(n)) {
                mat.remove(new Pair<>(e.getFrom(), e.getTo()));
            }
        }
        edges.removeIf(x -> x.getFrom().equals(n) || x.getTo().equals(n));
    }

    @Override
    public void removeEdgesContaining(T n) {
        removeEdgesContaining(getValue(n));
    }

    @Override
    public String toString() {
        return "AMGraph{" +
                "mat=" + mat +
                ", nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
