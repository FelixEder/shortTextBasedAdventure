import java.util.Scanner;

/**
 * The Parser class reads the player input and recognizes it as commands
 * @author felix
 *
 */
public class Parser {
	private CommandDictionary commands;
	
	 /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandDictionary();
    }
	
    /**
     * @return The next command from the user.
     */
    public InputCommand getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        //System.out.print("> ");     // print prompt

        inputLine = Main.getTextField();

        // Find up to two words on the line.
        @SuppressWarnings("resource")
		Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next().toLowerCase();  // get second word to lower case
                // note: we just ignore the rest of the input line.
            }
        }

        return new InputCommand(commands.getCommandWord(word1), word2);
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
    
    /**
     * @return the string command associated with the HELP-commands.
     */
    public String getSpecificKey() {
        return commands.getHelpString();
    }
}
