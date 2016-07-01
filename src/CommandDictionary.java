import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 *
 * @author  (Felix Eder)
 */

public class CommandDictionary
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, enumCommands> validCommands;

    /**
     * Constructor - initialize the command words.
     */
    public CommandDictionary() {
        validCommands = new HashMap<String, enumCommands>();
        for(enumCommands command : enumCommands.values()) {
            validCommands.put(command.toString(), command);
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord corresponding to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public enumCommands getCommandWord(String commandWord)	{
    	return validCommands.get(commandWord);
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
    
    /**
     * @return The command associated with the HELP-command.
     */
    public String getHelpString() {
        return enumCommands.HELP.toString();
    }
}
