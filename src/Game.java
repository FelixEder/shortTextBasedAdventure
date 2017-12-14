import javafx.stage.Stage;

public class Game {
	private Parser parser;
	private Player player;
	private String language;
	private PlayerActions playerActions;
	private GameSetup gameSetup;
	private Controller controller;
	
	/**
	 * Constructor for game
	 * @param language The language to be played
	 * @param playerName The name of the game.
	 */
	public Game(String language, String playerName, Controller controller) {
		parser = new Parser(controller);
		player = new Player(playerName, controller);
		this.language = language;
		this.controller = controller;
		playerActions = new PlayerActions(player, controller);
		gameSetup = new GameSetup(player);
		gameSetup.setUpGame();
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
    controller.printGameInfo("Thanks for enjoying my little game, have a nice day." + "\n");
	}
	
	/**
	 * The text that is printed out in beginning of the game.
	 */
	private void printStartMessage() {
		controller.printGameInfo("");
		controller.printGameInfo("Welcome to my game!");
		controller.printGameInfo("");
		controller.printGameInfo("You are on an expedition in the Andes of South America.");
		controller.printGameInfo("However, you are currently falling down a cliff.");
		controller.printGameInfo("Suddenly, you land on a small platform");
		controller.printGameInfo("Type '" + parser.getSpecificKey() + "' if you need help.");
		controller.printGameInfo("");
		playerActions.printLocationInfo();
	}
	
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() {
      controller.printGameInfo("You are lost. You are alone. You wander");
      controller.printGameInfo("around the Andes.");
      controller.printGameInfo("");
      controller.printGameInfo("Your command words are:");
      parser.showCommands();
      controller.printGameInfo("");
      controller.printGameInfo("");
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
			
		default:
      controller.printGameInfo("I don't know what you mean..." + "\n");
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
          controller.printGameInfo("Quit what?" + "\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}