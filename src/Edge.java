/**
 * The type Edge.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 18/04/18
 * @project 2511Project
 */
public class Edge<T> {
    private int cost;
    private Node<T> from;
    private Node<T> to;

    /**
     * Instantiates a new Edge.
     *
     * @param from the from node
     * @param to   the to node
     * @param cost the cost
     */
    public Edge(Node<T> from, Node<T> to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    /**
     * Gets the from node.
     *
     * @return the from node
     */
    public Node<T> getFrom() {
        return from;
    }

    /**
     * Sets from node.
     *
     * @param from the from
     */
    public void setFrom(Node<T> from) {
        this.from = from;
    }

    /**
     * Gets the to node.
     *
     * @return the to node
     */
    public Node<T> getTo() {
        return to;
    }

    /**
     * Sets the to node.
     *
     * @param to the to node
     */
    public void setTo(Node<T> to) {
        this.to = to;
    }

    /**
     * Gets the cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost.
     *
     * @param cost the cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Edge && ((Edge) o).cost == cost && ((Edge) o).from.equals(from) && ((Edge) o).to.equals(to));
    }
}
