
public class Game {
	private Parser parser;
	private Player player;
	private String language;
	
	/**
	 * Main method, creates the game and starts it.
	 * @param args The first word is the language to be played, the second is the name of the player
	 */
	public static void main(String[] args) {
		Game game = new Game(args[0], args[1]);
		game.play();

	}
	
	/**
	 * Constructor for game
	 * @param language The language to be played
	 * @param playerName The name of the game.
	 */
	public Game(String language, String playerName) {
		parser = new Parser();
		player = new Player(playerName);
		this.language = language;
		setUpGame();
	}

	/**
	 * Creates all the different rooms in the game and sets all exits.
	 */
	private void setUpGame() {
		Room platform, camp, techInSnow;
		
		platform = new Room(" on a small platform sticking out from the mountain. There is a long way up and even longer way down. Up is preferable.");
		camp = new Room(" in a small encampment. There is a camp fire, small tent and a bunch of junk.");
		techInSnow = new Room(" on snow-covered plain. There isn't much here except for a strange box half buried in the snow.");
		
		platform.setExit("up", camp);
		camp.setExit("west", techInSnow);
		
		player.setCurrentRoom(platform);
		
		Item icePicks, snowPile;
		
		icePicks = new Item("Ice picks", "two pair of ice picks, makes for a good grip on ice.", false, null);
		snowPile = new Item("Snow pile", "A medium sized pile of snow, the one you made when you fell down here.", true, icePicks);
		snowPile.setSearchedText("Searching the pile of snow, you find " + snowPile.getContains().getDescription());
		
		platform.addItem(snowPile);
		
	}
	
	/**
	 * Starts the infinite loop that is used when the game is running
	 */
	public void play() {
		printStartMessage();
		
		boolean done = false;
		while(!done) {
			Command command = parser.getCommand();
			done = processCommand(command);
		}
		System.out.println("Thanks for enjoying my little game, have a nice day.");
	}
	
	/**
	 * The text that is printed out in beginning of the game.
	 */
	private void printStartMessage() {
		System.out.println();
		System.out.println("Welcome to my game!");
        System.out.println("Type '" + parser.getSpecificKey() + "' if you need help.");
        System.out.println();
		System.out.println("You are on an expedition in the Andes of South America.");
		System.out.println("However, you are currently falling down a cliff.");
		System.out.println("Suddenly, you land on a small platform");
		System.out.println();
		printLocationInfo();
	}
	
	/**
	 * Processes the commands from the player
	 * @param command The command from the player
	 * @return The boolean regarding if the player wants to quit the game
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		enumCommands commandWord = command.getCommandWord();
		
		switch(commandWord) {
		//Add more cases as the number of commands increases.
		case GO:
			goRoom(command);
			break;
			
		case HELP:
			printHelp();
			break;
		
		case SEARCH:
			search(command);
			break;
			
		case QUIT:
			wantToQuit = quit(command);
			break;
			
		default:   
			System.out.println("I don't know what you mean..." + "\n");
            break;
		}
		return wantToQuit;
	}
	
	   /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?" + "\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
	
    /**
     * Searches a given item
     * @param command The command typed by the player.
     */
    private void search(Command command) {
    	if(!command.hasSecondWord()) {
    		System.out.println("Search what?" + "/n");
    		return;
    	}
    	String item = command.getSecondWord();
    	Item itemToSearch = player.getCurrentRoom().getRoomItem(item);
    	if(itemToSearch == null) {
    		System.out.println("There is no such item in this area!" + "/n");
    		return;
    	}
    	else if(!itemToSearch.isSearchable()){
    		System.out.println(itemToSearch.getName() + " can't be searched!");
    		return;
    	}
    	else {
    		itemToSearch.getSearchedText();
    		System.out.println("You picked up " + itemToSearch.getContains().getName());
    		player.setInventory(itemToSearch.getContains());
    		player.getCurrentRoom().removeItemFromRoom(itemToSearch.getName());
    	}
    }
    
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Andes.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
        System.out.println();
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * Some rooms require certain items to access.
     * @param command The command typed by the player.
     */
	private void goRoom(Command command) {
		if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?" + "\n");
            return;
        }
		String direction = command.getSecondWord();
		Room nextRoom = player.getCurrentRoom().getExit(direction);
		 if (nextRoom == null) {
	            System.out.println("There is no exit in that direction!" + "\n");
		 }
		 else {
			 player.setRoomHistory();
			 player.setCurrentRoom(nextRoom);
			 printLocationInfo();
		 }
	}
	
	/**
	 * Prints the complete information regarding a room and it's content
	 */
	private void printLocationInfo() {
		System.out.println(player.getCurrentRoom().getCompleteDescription());
	}
}