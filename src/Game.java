
public class Game {
	private Parser parser;
	private Player player;
	private String language;
	
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
     * Searches a given item
     * @param inputCommand The command typed by the player.
     */
    private void search(InputCommand inputCommand) {
    	if(!inputCommand.hasSecondWord()) {
    		Main.printGameInfo("Search what?" + "\n");
    		return;
    	}
    	String item = inputCommand.getSecondWord();
    	Item itemToSearch = player.getCurrentRoom().getRoomItem(item);
    	if(itemToSearch == null) {
    		Main.printGameInfo("There is no such item in this area!" + "\n");
    		return;
    	}
    	else if(!itemToSearch.isSearchable()){
    		Main.printGameInfo(itemToSearch.getName() + " can't be searched!" + "\n");
    		return;
    	}
    	else {
    		Main.printGameInfo(itemToSearch.getSearchedText());
    		Main.printGameInfo("You picked up " + itemToSearch.getContains().getName() + "\n");
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
        Main.printGameInfo("You are lost. You are alone. You wander");
        Main.printGameInfo("around the Andes.");
        Main.printGameInfo("");
        Main.printGameInfo("Your command words are:");
        parser.showCommands();
        Main.printGameInfo("");
        Main.printGameInfo("");
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
            Main.printGameInfo("Go where?" + "\n");
            return;
        }
		String direction = inputCommand.getSecondWord();
		Room.ExitReq nextRoom = player.getCurrentRoom().getExit(direction);
		 if (nextRoom == null) {
	            Main.printGameInfo("There is no exit in that direction!" + "\n");
		 }
		 else if(nextRoom.itemNeeded == null || player.getInventory(nextRoom.itemNeeded.getName()) != null){
			 player.setRoomHistory();
			 player.setCurrentRoom(nextRoom.exitRoom);
			 Main.printGameInfo(nextRoom.successExit + "\n");
			 printLocationInfo();
		 }
		 else {
			 Main.printGameInfo(nextRoom.failedExit + "\n");
		 }
	}
	
    /**
     * If player has left starting area, goes back to the previous room.
     * @param inputCommand The command written by the player.
     */
    private void back(InputCommand inputCommand) {
        if(inputCommand.hasSecondWord()) {
            if(inputCommand.getSecondWord().equals("back")) {
                Main.printGameInfo("Back back where?" + "\n");
            }
            else {
                Main.printGameInfo("Back where?" + "\n");
            }
       } 
        else if(!player.checkIfEmpty()) {
           player.setCurrentRoom(player.getRoomHistory()); 
           printLocationInfo();
        }
        else {
            Main.printGameInfo("From here on, you can't go back further." + "\n" );
        }
    }
	
    /**
     * Picks up an item and adds to player inventory. 
     * In some cases an other item maybe needed in inventory.
     * @param inputCommand The command written by the player.
     */
    private void pickUpItem(InputCommand inputCommand) {
        if(!inputCommand.hasSecondWord()) {
            Main.printGameInfo("Take what?" + "\n");
            return;
        }
        else if(player.getCurrentRoom().isItemInRoom(inputCommand.getSecondWord())) {
        	Item itemToPick = player.getCurrentRoom().getRoomItem(inputCommand.getSecondWord());
        	if(itemToPick.isLiftable()) {
                    player.setInventory(itemToPick);
                    Main.printGameInfo("Picked up " + itemToPick.getName() + "." + "\n");
                    return;
                } 
        	else {
        		Main.printGameInfo(itemToPick.getName() + " can't be picked up!" + "\n");
        		return;
        	}
        }
        else {
            Main.printGameInfo("The mentioned item is not located in the room!" + "\n");
        }
    }
    
    /**
     * Drops an item from player inventory.
     * @param inputCommand The command written by the player.
     */
    private void dropItems(InputCommand inputCommand) {
        if(!inputCommand.hasSecondWord()) {
            Main.printGameInfo("Drop what?" + "\n");
            return;
        }
        Item itemToDrop = player.getInventory(inputCommand.getSecondWord());
        if(itemToDrop != null) {
            player.removeInventory(itemToDrop.getName());
       }
        else {
            Main.printGameInfo("The mentioned item is not in your inventory!" + "\n");
        }
    }
	
	/**
	 * Prints the complete information regarding a room and it's content
	 */
	private void printLocationInfo() {
		Main.printGameInfo(player.getCurrentRoom().getCompleteDescription());
	}
}