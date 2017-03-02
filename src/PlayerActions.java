/**
 * This class takes care of all the actions made by the player.
 * @author felix
 *
 */
public class PlayerActions {
    Player player;
	Game game;
	
	/**
	 * Constructor of the class, sets it up.
	 * @param player The player of the game
	 * @param game The game of the player.
	 */
	public PlayerActions(Player player, Game game) {
		this.player = player;
		this.game = game;
	}
	
	/**
	 * Prints the complete information regarding a room and it's content
	 */
	public void printLocationInfo() {
		Main.printGameInfo(player.getCurrentRoom().getCompleteDescription());
	}
	
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * Some rooms require certain items to access.
     * @param inputCommand The command typed by the player.
     */
	public void goRoom(InputCommand inputCommand) {
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
	public void back(InputCommand inputCommand) {
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
	public void pickUpItem(InputCommand inputCommand) {
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
	public void dropItems(InputCommand inputCommand) {
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
     * Searches a given item
     * @param inputCommand The command typed by the player.
     */
    public void search(InputCommand inputCommand) {
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

    	else if(!player.hasItem(itemToSearch.getContains().itemNeeded) && !(itemToSearch.getContains().itemNeeded == null)) {
    		Main.printGameInfo("You try searching it, but you can't get it open." + "\n" +
    				"It seems like you need another item.");
    	}
    
    	else {
    		Main.printGameInfo(itemToSearch.getContains().successSearch + "\n");
    		for(int i = 0; i < itemToSearch.getContains().storedItems.length; i++) {
    			Main.printGameInfo(itemToSearch.getContains().storedItems[i].getDescription() + "\n");
        		player.setInventory(itemToSearch.getContains().storedItems[i]);
    		}
    		player.getCurrentRoom().removeItemFromRoom(itemToSearch.getName());
    	}
    }
}
