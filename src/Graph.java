import java.util.ArrayList;

/**
 * The interface Graph.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 18/04/18
 * @project 2511Project
 */
interface Graph<T> {

    /**
     * Add a node to the graph.
     *
     * @param n the node
     */
    void addNode(Node<T> n);

    /**
     * Gets a node from the graph, if it already exists then an error will be thrown.
     *
     * @param v the value
     * @return the node
     */
    Node<T> getNode(T v);

    /**
     * Gets the list of nodes currently on the graph.
     *
     * @return the nodes on the graph
     */
    ArrayList<Node<T>> getNodes();

    /**
     * Remove a node on the graph, if it exists.
     *
     * @param n the node
     */
    void removeNode(Node<T> n);

    /**
     * Add a value as a node to the graph.
     *
     * @param v the value
     * @return the node added
     */
    Node<T> addValue(T v);

    /**
     * Gets a value on the graph, if it exists. If it doesn't then null will be returned.
     *
     * @param v the value
     * @return the node found
     */
    Node<T> getValue(T v);

    /**
     * Remove a value, if it exists on the graph.
     *
     * @param v the value
     */
    void removeValue(T v);

    /**
     * Add an edge to the graph, if it already exists then it will be updated with the new values or specs.
     *
     * @param from        the from node
     * @param to          the to node
     * @param cost        the cost
     * @param directional the directional boolean
     */
    void addEdge(Node<T> from, Node<T> to, int cost, boolean directional);

    /**
     * Add an edge to the graph, if it already exists then it will be updated with the new values or specs.
     *
     * @param from        the from value
     * @param to          the to value
     * @param cost        the cost
     * @param directional the directional boolean
     */
    void addEdge(T from, T to, int cost, boolean directional);

    /**
     * Gets an edge if it is present on the graph, otherwise it will return null.
     *
     * @param from the from node
     * @param to   the to node
     * @return the edge
     */
    Edge<T> getEdge(Node<T> from, Node<T> to);

    /**
     * Gets an edge if it is present on the graph, otherwise it will return null.
     *
     * @param from the from node
     * @param to   the to node
     * @return the edge
     */
    Edge<T> getEdge(T from, T to);

    /**
     * Gets the list of all edges on the graph.
     *
     * @return the edges list
     */
    ArrayList<Edge<T>> getEdges();

    /**
     * Gets all edges going from the node provided.
     *
     * @param n the node
     * @return the list of edges from n
     */
    ArrayList<Edge<T>> getEdgesFrom(Node<T> n);

    /**
     * Gets all edges going from the node provided.
     *
     * @param n the node
     * @return the list of edges from n
     */
    ArrayList<Edge<T>> getEdgesFrom(T n);

    /**
     * Remove an edge from the graph if it exists.
     *
     * @param from the from node
     * @param to   the to node
     */
    void removeEdge(Node<T> from, Node<T> to);

    /**
     * Remove an edge from the graph if it exists.
     *
     * @param from the from node
     * @param to   the to node
     */
    void removeEdge(T from, T to);

    /**
     * Remove any edges that contain the provided node.
     *
     * @param n the node
     */
    void removeEdgesContaining(Node<T> n);

    /**
     * Remove any edges that contain the provided node.
     *
     * @param n the node
     */
    void removeEdgesContaining(T n);
}
