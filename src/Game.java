
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
		
		platform = new Room(" on a small platform sticking out from the mountain. There is a long way up" +
				" and even longer way down. Up is preferable.");
		camp = new Room(" in a small encampment. There is a camp fire, small tent and a bunch of junk.");
		techInSnow = new Room(" on snow-covered plain. There isn't much here except for a strange box half buried in the snow.");
	
		player.setCurrentRoom(platform);
		
		Item icePicks, snowPile;
		
		icePicks = new Item("Icepicks", "two pair of ice picks, makes for a good grip on ice.", false, null, true);
		snowPile = new Item("Snowpile", "a medium sized pile of snow, the one you made when you fell down here.", true, icePicks, false);
		snowPile.setSearchedText("Searching the pile of snow, you find " + snowPile.getContains().getDescription());
		
		platform.addItem(snowPile);
		
		platform.setExit("up", camp, "You try to climb the icy wall,  only to lose your grip and fall down on the pile of snow",
		"With your ice picks, you scale the wall as if it was horizontal.",icePicks);
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
		System.out.println("Thanks for enjoying my little game, have a nice day." + "\n");
	}
	
	/**
	 * The text that is printed out in beginning of the game.
	 */
	private void printStartMessage() {
		System.out.println();
		System.out.println("Welcome to my game!");
        System.out.println();
		System.out.println("You are on an expedition in the Andes of South America.");
		System.out.println("However, you are currently falling down a cliff.");
		System.out.println("Suddenly, you land on a small platform");
        System.out.println("Type '" + parser.getSpecificKey() + "' if you need help.");
		System.out.println();
		printLocationInfo();
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
			goRoom(inputCommand);
			break;
			
		case HELP:
			printHelp();
			break;
		
		case SEARCH:
			search(inputCommand);
			break;
			
		case TAKE:
			pickUpItem(inputCommand);
			break;
			
		case QUIT:
			wantToQuit = quit(inputCommand);
			break;
			
		case LOOK:
			printLocationInfo();
			break;

		case BACK:
			back(inputCommand);
			break;
	          
		case DROP:
			dropItems(inputCommand);
			break;
	          
		case ITEMS:
			System.out.println(player.getInventoryString() + "\n");
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
    private boolean quit(InputCommand inputCommand) 
    {
        if(inputCommand.hasSecondWord()) {
            System.out.println("Quit what?" + "\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
	
    /**
     * Searches a given item
     * @param inputCommand The command typed by the player.
     */
    private void search(InputCommand inputCommand) {
    	if(!inputCommand.hasSecondWord()) {
    		System.out.println("Search what?" + "\n");
    		return;
    	}
    	String item = inputCommand.getSecondWord();
    	Item itemToSearch = player.getCurrentRoom().getRoomItem(item);
    	if(itemToSearch == null) {
    		System.out.println("There is no such item in this area!" + "\n");
    		return;
    	}
    	else if(!itemToSearch.isSearchable()){
    		System.out.println(itemToSearch.getName() + " can't be searched!" + "\n");
    		return;
    	}
    	else {
    		System.out.println(itemToSearch.getSearchedText());
    		System.out.println("You picked up " + itemToSearch.getContains().getName() + "\n");
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
     * @param inputCommand The command typed by the player.
     */
	private void goRoom(InputCommand inputCommand) {
		if(!inputCommand.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?" + "\n");
            return;
        }
		String direction = inputCommand.getSecondWord();
		Room.ExitReq nextRoom = player.getCurrentRoom().getExit(direction);
		 if (nextRoom == null) {
	            System.out.println("There is no exit in that direction!" + "\n");
		 }
		 else if(nextRoom.itemNeeded == null || player.getInventory(nextRoom.itemNeeded.getName()) != null){
			 player.setRoomHistory();
			 player.setCurrentRoom(nextRoom.exitRoom);
			 System.out.println(nextRoom.successExit + "\n");
			 printLocationInfo();
		 }
		 else {
			 System.out.println(nextRoom.failedExit + "\n");
		 }
	}
	
    /**
     * If player has left starting area, goes back to the previous room.
     * @param inputCommand The command written by the player.
     */
    private void back(InputCommand inputCommand) {
        if(inputCommand.hasSecondWord()) {
            if(inputCommand.getSecondWord().equals("back")) {
                System.out.println("Back back where?" + "\n");
            }
            else {
                System.out.println("Back where?" + "\n");
            }
       } 
        else if(!player.checkIfEmpty()) {
           player.setCurrentRoom(player.getRoomHistory()); 
           printLocationInfo();
        }
        else {
            System.out.println("From here on, you can't go back further." + "\n" );
        }
    }
	
    /**
     * Picks up an item and adds to player inventory. 
     * In some cases an other item maybe needed in inventory.
     * @param inputCommand The command written by the player.
     */
    private void pickUpItem(InputCommand inputCommand) {
        if(!inputCommand.hasSecondWord()) {
            System.out.println("Take what?" + "\n");
            return;
        }
        else if(player.getCurrentRoom().isItemInRoom(inputCommand.getSecondWord())) {
        	Item itemToPick = player.getCurrentRoom().getRoomItem(inputCommand.getSecondWord());
        	if(itemToPick.isLiftable()) {
                    player.setInventory(itemToPick);
                    System.out.println("Picked up " + itemToPick.getName() + "." + "\n");
                    return;
                } 
        	else {
        		System.out.println(itemToPick.getName() + " can't be picked up!" + "\n");
        		return;
        	}
        }
        else {
            System.out.println("The mentioned item is not located in the room!" + "\n");
        }
    }
    
    /**
     * Drops an item from player inventory.
     * @param inputCommand The command written by the player.
     */
    private void dropItems(InputCommand inputCommand) {
        if(!inputCommand.hasSecondWord()) {
            System.out.println("Drop what?" + "\n");
            return;
        }
        Item itemToDrop = player.getInventory(inputCommand.getSecondWord());
        if(itemToDrop != null) {
            player.removeInventory(itemToDrop.getName());
       }
        else {
            System.out.println("The mentioned item is not in your inventory!" + "\n");
        }
    }
	
	/**
	 * Prints the complete information regarding a room and it's content
	 */
	private void printLocationInfo() {
		System.out.println(player.getCurrentRoom().getCompleteDescription());
	}
}