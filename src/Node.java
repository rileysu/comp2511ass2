import java.util.Objects;

/**
 * The type Node.
 *
 * @param <T> the type parameter
 * @author Riley Sutton on 18/04/18
 * @project 2511Project
 */
public class Node<T> {
    private T value;

    /**
     * Instantiates a new Node.
     *
     * @param v the value
     */
    public Node(T v) {
        value = v;
    }

    /**
     * Sets the value.
     *
     * @param v the value
     */
    public void setValue(T v) {
        value = v;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Node && ((Node) o).value.equals(value));
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
