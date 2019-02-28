/**
 * The type Shipment command.
 *
 * @author Riley Sutton on 19/04/18
 * @project Comp2511 Ass2
 */
public class ShipmentCommand extends Command{
    private String command;
    private String name1;
    private String name2;

    /**
     * Instantiates a new Shipment command.
     *
     * @param command the command
     * @param name1   name 1
     * @param name2   name 2
     */
    public ShipmentCommand(String command, String name1, String name2) {
        this.command = command;
        this.name1 = name1;
        this.name2 = name2;
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
