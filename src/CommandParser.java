import java.text.ParseException;

/**
 * The type Command parser.
 *
 * @author Riley Sutton on 25/03/18
 * @project Comp2511 Ass1
 */
public class CommandParser {

    /**
     * Instantiates a new Command parser.
     */
    public CommandParser(){
    }

    /**
     * Parse name String.
     *
     * @param nameString the name string
     * @return the name
     */
    String parseName(String nameString){
        return nameString;
    }

    /**
     * Parse time integer.
     *
     * @param timeString the time string
     * @return the time
     * @throws ParseException the parse exception
     */
    int parseTime(String timeString) throws ParseException {
        return Integer.parseInt(timeString);
    }


    /**
     * Parse command.
     *
     * @param comstring the command string
     * @return the command
     */
    Command parseCommand(String comstring) {

        Command command = null;

        if (comstring.contains("#")){
            comstring = comstring.split("#")[0];
        }

        String[] proc = comstring.split(" ");

        // If the input was empty then abort
        if (proc.length == 0) {
            return null;
        }

        String name1;
        String name2;
        int time;

        // Switch statement for valid commands
        // Try and catch to parse expected arguments
        switch (proc[0].toLowerCase()) {
            case "refuelling":
                try{
                    time = parseTime(proc[1]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                try{
                    name1 = parseName(proc[2]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                command = new RefuellingCommand(proc[0],time,name1);
                break;
            case "time":
                try{
                    time = parseTime(proc[1]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                try{
                    name1 = parseName(proc[2]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                try{
                    name2 = parseName(proc[3]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                command = new TimeCommand(proc[0], time, name1, name2);

                break;
            case "shipment":
                try {
                    name1 = parseName(proc[1]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                try{
                    name2 = parseName(proc[2]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

                command = new ShipmentCommand(proc[0], name1, name2);
                break;
            default:
                break;
        }

        // Return null if something goes wrong (we will ignore null commands later)

        return command;
    }

}
