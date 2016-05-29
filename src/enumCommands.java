
/**
 * Enumeration class CommandWord 
 * Creates enumerates for commandwords, swell as names in different languages.
 * 
 * @author (Felix Eder)
 */
public enum enumCommands
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go", "gehen"), QUIT("quit", "beenden"), HELP("help", null), LOOK("look", null), SCREAM("scream", null), BACK("back", null), TAKE("take", null)
    , DROP("drop", null), ITEMS("items", null), UNKNOWN("?", null), SEARCH("search", "suche");
    
    // The command string.
    private String commandString;
    private String translation;
    
    /**
     * Initialise with the corresponding command string and translation.
     * @param commandString The command string.
     */
    enumCommands(String commandString, String translated)
    {
        this.commandString = commandString;
        translation = translated;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
    
    /**
     * @return The translated word as string.
     */
    public String getTranslation() {
        return translation;
    }
}

