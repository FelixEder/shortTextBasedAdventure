
/**
 * This class takes care of all the actions made by the player.
 * @author Felix
 *
 */
public class PlayerActions {
	private Player player;
	private Controller controller;

	/**
	 * Constructor of the class, sets it up.
	 * @param player The player of the game
	 */
	public PlayerActions(Player player, Controller controller) {
		this.controller = controller;
		this.player = player;
	}

	/**
	 * Prints the complete information regarding a room and it's content
	 */
	public void printLocationInfo() {
		controller.printGameInfo(player.getCurrentRoom().getCompleteDescription());
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
			controller.printGameInfo("Go where?" + "\n");
						return;
				}
		String direction = inputCommand.getSecondWord();
		Room.ExitReq nextRoom = player.getCurrentRoom().getExit(direction);
		 if (nextRoom == null) {
			 controller.printGameInfo("There is no exit in that direction!" + "\n");
		 }
		 else if(nextRoom.itemNeeded == null || player.getInventory(nextRoom.itemNeeded.getName()) != null){
			 player.setRoomHistory();
			 player.setCurrentRoom(nextRoom.exitRoom);
			 controller.printGameInfo(nextRoom.successExit + "\n");
			 printLocationInfo();
		 }
		 else {
			 controller.printGameInfo(nextRoom.failedExit + "\n");
		 }
	}

	/**
	 * If player has left starting area, goes back to the previous room.
	 * @param inputCommand The command written by the player.
	 */
  public void back(InputCommand inputCommand) {
    if(inputCommand.hasSecondWord()) {
        if(inputCommand.getSecondWord().equals("back")) {
          controller.printGameInfo("Back back where?" + "\n");
        }
        else {
          controller.printGameInfo("Back where?" + "\n");
        }
   }
    else if(!player.checkIfEmpty()) {
       player.setCurrentRoom(player.getRoomHistory());
       printLocationInfo();
    }
    else {
      controller.printGameInfo("From here on, you can't go back further." + "\n" );
    }
	}

	/**
	 * Picks up an item and adds to player inventory.
	 * In some cases an other item maybe needed in inventory.
	 * @param inputCommand The command written by the player.
	 */
  public void pickUpItem(InputCommand inputCommand) {
    if(!inputCommand.hasSecondWord()) {
      controller.printGameInfo("Take what?" + "\n");
    }
    else if(player.getCurrentRoom().isItemInRoom(inputCommand.getSecondWord())) {
      Element elemToPick = player.getCurrentRoom().getRoomItem(inputCommand.getSecondWord());
      if(elemToPick instanceof Item) {
        if(((Item) elemToPick).isLiftable()) {
          player.addItemToInventory((Item) elemToPick);
          controller.printGameInfo("Picked up " + elemToPick.getName() + "." + "\n");
        }
        else {
          controller.printGameInfo(elemToPick.getName() + " can't be picked up!" + "\n");
        }
      } else {
        controller.printGameInfo(((Collectibles) elemToPick).getpickUpTextText());
        player.getCurrentRoom().removeItemFromRoom(elemToPick.getName());
      }
    }
    else {
      controller.printGameInfo("The mentioned item is not located in the room!" + "\n");
    }
	}

	/**
	 * Drops an item from player inventory.
	 * @param inputCommand The command written by the player.
	 */
  public void dropItems(InputCommand inputCommand) {
    if(!inputCommand.hasSecondWord()) {
      controller.printGameInfo("Drop what?" + "\n");
        return;
    }
    Item itemToDrop = player.getInventory(inputCommand.getSecondWord());
    if(itemToDrop != null) player.removeItemFromInventory(itemToDrop.getName());
    else controller.printGameInfo("The mentioned item is not in your inventory!" + "\n");
	}

	/**
	 * Searches a given item
	 * @param inputCommand The command typed by the player.
	 */
	public void search(InputCommand inputCommand) {
		if(!inputCommand.hasSecondWord()) {
			controller.printGameInfo("Search what?" + "\n");
			return;
		}
		String item = inputCommand.getSecondWord();
		Element elementToSearch = player.getCurrentRoom().getRoomItem(item);
		Item itemToSearch;
		if(elementToSearch instanceof Collectibles) {
			controller.printGameInfo(elementToSearch.getName() + " can't be searched!" + "\n");
			return;
		}
		itemToSearch = (Item) elementToSearch;
		if(itemToSearch == null) {
			controller.printGameInfo("There is no such item in this area!" + "\n");
			return;
		}
		else if(!itemToSearch.isSearchable()){
			controller.printGameInfo(itemToSearch.getName() + " can't be searched!" + "\n");
			return;
		}

		else if(!player.hasItem(itemToSearch.getContains().itemNeeded) && !(itemToSearch.getContains().itemNeeded == null)) {
			controller.printGameInfo(itemToSearch.getContains().failedSearch + "\n");
		}

		else {
			controller.printGameInfo(itemToSearch.getContains().successSearch + "\n");
			for(int i = 0; i < itemToSearch.getContains().storedItems.length; i++) {
				Element elem = itemToSearch.getContains().storedItems[i];
				controller.printGameInfo(elem.getDescription() + "\n");
				if(elem instanceof Item)
					player.addItemToInventory((Item) elem);
				else {
					controller.printGameInfo(((Collectibles) elem).getpickUpTextText());
				}
			}
			player.getCurrentRoom().removeItemFromRoom(itemToSearch.getName());
		}
	}
}