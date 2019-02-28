/**
 * The Refuelling command.
 *
 * @author Riley Sutton on 19/04/18
 * @project Comp2511 Ass2
 */
public class RefuellingCommand extends Command{
    private String command;
    private int time;
    private String name;

    /**
     * Instantiates a new Refuelling command.
     *
     * @param time the time cost
     * @param name the name
     */
    public RefuellingCommand(String command, int time, String name) {
        this.command = command;
        this.time = time;
        this.name = name;
    }

    /**
     * Gets the time cost.
     *
     * @return the time cost
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time cost.
     *
     * @param time the time cost
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
