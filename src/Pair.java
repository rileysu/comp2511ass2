import java.util.Objects;

/**
 * The type Pair.
 *
 * @param <T1> the type parameter for term 1
 * @param <T2> the type parameter for term 2
 * @author Riley Sutton on 18/04/18
 * @project 2511Project
 */
public class Pair<T1, T2> {
    private T1 t1;
    private T2 t2;

    /**
     * Instantiates a new Pair with null terms.
     */
    public Pair(){
        this.t1 = null;
        this.t2 = null;
    }

    /**
     * Instantiates a new Pair with provided terms.
     *
     * @param t1 term 1
     * @param t2 term 2
     */
    public Pair(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    /**
     * Gets term 1.
     *
     * @return term 1
     */
    public T1 getT1() {
        return t1;
    }

    /**
     * Sets term 1.
     *
     * @param t1 term 1
     */
    public void setT1(T1 t1) {
        this.t1 = t1;
    }

    /**
     * Gets term 2.
     *
     * @return term 2
     */
    public T2 getT2() {
        return t2;
    }

    /**
     * Sets term 2.
     *
     * @param t2 term 2
     */
    public void setT2(T2 t2) {
        this.t2 = t2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1, t2);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Pair && ((Pair) o).t1.equals(t1) && ((Pair) o).t2.equals(t2));
    }

    @Override
    public String toString(){
        return "[" + t1.toString() + ", " + t2.toString() + "]";
    }
}
