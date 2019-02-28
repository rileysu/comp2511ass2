/**
 * The type Request.
 *
 * @author Riley Sutton on 25/03/18
 * @project Comp2511 Ass1
 */
public abstract class Command {

    private String command = null;

    /**
     * Gets the command string.
     *
     * @return the command string
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the command string.
     *
     * @param command the command string
     */
    public void setCommand(String command) {
        this.command = command;
    }
}
