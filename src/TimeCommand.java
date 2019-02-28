/**
 * The type Time command.
 *
 * @author Riley Sutton on 19/04/18
 * @project Comp2511 Ass2
 */
public class TimeCommand extends Command{
    private String command;
    private int time;
    private String name1;
    private String name2;

    /**
     * Instantiates a new Time command.
     *
     * @param command the command
     * @param time    the time cost
     * @param name1   name 1
     * @param name2   name 2
     */
    public TimeCommand(String command, int time, String name1, String name2) {
        this.command = command;
        this.time = time;
        this.name1 = name1;
        this.name2 = name2;
    }

    /**
     * Gets the time cost.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time cost.
     *
     * @param time the time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets name 1.
     *
     * @return name 1
     */
    public String getName1() {
        return name1;
    }

    /**
     * Sets name 1.
     *
     * @param name1 name 1
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * Gets name 2.
     *
     * @return name 2
     */
    public String getName2() {
        return name2;
    }

    /**
     * Sets name 2.
     *
     * @param name2 name 2
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }
}
