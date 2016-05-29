import java.util.HashMap;

/**
 * The Room class, which keeps information regarding the different rooms.
 * These include desription of room, exits to other rooms and the items in the room.
 * @author felix
 *
 */
public class Room {
	private String description;
	private HashMap<String, ExitItem> exits;
	private HashMap<String, Item> itemsInRoom;
	
	/**
	 * The constructor of room
	 * @param description The description of the room
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, ExitItem>();
		itemsInRoom = new HashMap<String, Item>();
	}
	
	/**
	 * Nested class that sets requirements for when exiting to another room
	 * @author felix
	 *
	 */
	public class ExitItem {
		public Room exitRoom;
		public String failedExit, successExit;
		public Item itemNeeded;
		
		private ExitItem(Room exitRoom, String failedExit, String successExit, Item itemNeeded) {
			this.exitRoom = exitRoom;
			this.failedExit = failedExit;
			this.successExit = successExit;
			this.itemNeeded = itemNeeded;
		}
	}
	
	/**
	 * Sets the adjacent rooms of the room  in a HashMap
	 * @param direction The exits to the other rooms
	 * @param neighboor The adjacent rooms
	 */
	public void setExit(String direction, Room exitRoom, String failedExit, String successExit, Item itemNeeded) {
		ExitItem exitInfo = new ExitItem(exitRoom, failedExit, successExit, itemNeeded);
		exits.put(direction, exitInfo);
	}
	
	/**
	 * Returns a chosen adjacent room
	 * @param direction The direction of the exit
	 * @return The room the exit leads to
	 */
	public Room getExit(String direction) {
		return exits.get(direction).exitRoom;
	}
	
	/**
	 * Removes an exit from the collection of exits
	 * @param direction The direction to the exit to be removed
	 */
	public void removeExit(String direction) {
		exits.remove(direction);
	}
	
    /**
     * @return The current exits available for the player
     */
    public String getExitString() {
        String returnString = "Exits:";
        for(String key : exits.keySet()) {
            returnString += " " + key;
        }
        return returnString;
    }
    
    /**
     * @return The items currently in the room
     */
    public String getItemString() {
        String returnString = "";
        for(Item listItems : itemsInRoom.values()) {
        	returnString += listItems.getDescription() + "\n";
        }
        return returnString;
    }
    
    /**
     *  @return A description of the room, including exits and items.
     */
    public String getCompleteDescription() {
        return "You are " + description + "\n" + getItemString() + "\n" + getExitString() + "\n";
    }
    
    /**
     * Checks whether item is in room
     * @param The item the player wants to check
     * @return The boolean value true if item is in room, return false if not
     */
    public boolean isItemInRoom(String roomItem) {
        return itemsInRoom.containsKey(roomItem);
    }
    
    /**
     * Returns the item asked for by the player
     * @param itemName The item the player wants to pick up
     * @return The item the player picked up
     */
    public Item getRoomItem(String itemName) {
        return itemsInRoom.get(itemName);
    }
    
    /**
     * Removes the item asked for by the player from the room
     * @param itemName The item to remove from the room
     */
    public void removeItemFromRoom(String itemName) {
        itemsInRoom.remove(itemName);
    }
    
    /**
     * Adds an item to the room.
     * @param itemName The String name of the item to add to the room
     * @param item The object item to add to the room
     */
    public void addItem(Item item) {
        itemsInRoom.put(item.getName(), item);
    }
}