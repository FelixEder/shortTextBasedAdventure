import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 *
 * @author  (Felix Eder)
 */

public class CommandDictionary {
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, EnumCommands> validCommands;
    private Controller controller;

    /**
     * Constructor - initialize the command words.
     */
    public CommandDictionary(Controller controller) {
      validCommands = new HashMap<>();
      for(EnumCommands command : EnumCommands.values()) {
          validCommands.put(command.toString(), command);
      }
      this.controller = controller;
      controller.updateCommandsText(validCommands.keySet());
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord corresponding to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public EnumCommands getCommandWord(String commandWord)	{
    	EnumCommands command = validCommands.get(commandWord);
    	if(command != null) return command;
    	return EnumCommands.UNKNOWN;
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String command) {
        return validCommands.containsKey(command);
    }

    /**
     * @return The command associated with the HELP-command.
     */
    public String getHelpString() {
        return EnumCommands.HELP.toString();
    }
}