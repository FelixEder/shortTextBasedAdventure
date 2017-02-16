
public class Game {
	private Parser parser;
	private Player player;
	private String language;
	private PlayerActions playerActions;
	
	/**
	 * Constructor for game
	 * @param language The language to be played
	 * @param playerName The name of the game.
	 */
	public Game(String language, String playerName) {
		parser = new Parser();
		player = new Player(playerName);
		this.language = language;
		playerActions = new PlayerActions(player, this);
		setUpGame();
	}

	/**
	 * Creates all the different rooms in the game and sets all exits.
	 */
	private void setUpGame() {
		Room platform, camp, techInSnow;
		
		platform = new Room("on a small platform sticking out from the mountain. There is a long way up" +
				" and even longer way down. Up is preferable.");
		camp = new Room("in a small encampment. There is a camp fire, small tent and a bunch of junk.");
		techInSnow = new Room("on snow-covered plain. There isn't much here except for a strange box half buried in the snow.");
	
		player.setCurrentRoom(platform);
		
		Item icepicks, snowpile;
		
		icepicks = new Item("ice picks", "two pair of ice picks, makes for a good grip on ice.", false, null, true);
		snowpile = new Item("snowpile", "a medium sized snowpile, the one you made when you fell down here.", true, icepicks, false);
		snowpile.setSearchedText("Searching the pile of snow, you find " + snowpile.getContains().getDescription());
		
		platform.addItem(snowpile);
		
		platform.setExit("up", camp, "You try to climb the icy wall,  only to lose your grip and fall down on the pile of snow",
		"With your ice picks, you scale the wall as if it was horizontal.",icepicks);
		camp.setExit("west", techInSnow, "", "", null);
		camp.setExit("down", platform, "", "", null);
		
	}
	
	/**
	 * Starts the infinite loop that is used when the game is running
	 */
	public void play() {
		printStartMessage();
		
		boolean done = false;
		while(!done) {
			InputCommand inputCommand = parser.getCommand();
			done = processCommand(inputCommand);
		}
		Main.printGameInfo("Thanks for enjoying my little game, have a nice day." + "\n");
	}
	
	/**
	 * The text that is printed out in beginning of the game.
	 */
	private void printStartMessage() {
		Main.printGameInfo("");
		Main.printGameInfo("Welcome to my game!");
        Main.printGameInfo("");
		Main.printGameInfo("You are on an expedition in the Andes of South America.");
		Main.printGameInfo("However, you are currently falling down a cliff.");
		Main.printGameInfo("Suddenly, you land on a small platform");
        Main.printGameInfo("Type '" + parser.getSpecificKey() + "' if you need help.");
		Main.printGameInfo("");
		playerActions.printLocationInfo();
	}
	
	/**
	 * Processes the commands from the player
	 * @param inputCommand The command from the player
	 * @return The boolean regarding if the player wants to quit the game
	 */
	private boolean processCommand(InputCommand inputCommand) {
		boolean wantToQuit = false;
		EnumCommands commandWord = inputCommand.getCommandWord();
		
		switch(commandWord) {
		//Add more cases as the number of commands increases.
		case GO:
			playerActions.goRoom(inputCommand);
			break;
			
		case HELP:
			printHelp();
			break;
		
		case SEARCH:
			playerActions.search(inputCommand);
			break;
			
		case TAKE:
			playerActions.pickUpItem(inputCommand);
			break;
			
		case QUIT:
			wantToQuit = quit(inputCommand);
			break;
			
		case LOOK:
			playerActions.printLocationInfo();
			break;

		case BACK:
			playerActions.back(inputCommand);
			break;
	          
		case DROP:
			playerActions.dropItems(inputCommand);
			break;
	          
		case ITEMS:
			Main.printGameInfo(player.getInventoryString() + "\n");
			break;
			
		default:   
			Main.printGameInfo("I don't know what you mean..." + "\n");
            break;
		}
		return wantToQuit;
	}
	
	   /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(InputCommand inputCommand) 
    {
        if(inputCommand.hasSecondWord()) {
            Main.printGameInfo("Quit what?" + "\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        Main.printGameInfo("You are lost. You are alone. You wander");
        Main.printGameInfo("around the Andes.");
        Main.printGameInfo("");
        Main.printGameInfo("Your command words are:");
        parser.showCommands();
        Main.printGameInfo("");
        Main.printGameInfo("");
    }

}