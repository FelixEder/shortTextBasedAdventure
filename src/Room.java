import java.util.HashMap;

/**
 * The Room class, which keeps information regarding the different rooms.
 * These include description of room, exits to other rooms and the items in the room.
 * @author felix
 *
 */
public class Room {
	private String description;
	private HashMap<String, ExitReq> exits;
	private HashMap<String, Element> elementsInRoom;
	
	/**
	 * The constructor of room
	 * @param description The description of the room
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, ExitReq>();
		elementsInRoom = new HashMap<String, Element>();
	}
	
	/**
	 * Nested class that sets requirements for when exiting to another room
	 */
	public class ExitReq {
		public Room exitRoom;
		public String failedExit, successExit;
		public Item itemNeeded;
		
		private ExitReq(Room exitRoom, String failedExit, String successExit, Item itemNeeded) {
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
		ExitReq exitInfo = new ExitReq(exitRoom, failedExit, successExit, itemNeeded);
		exits.put(direction, exitInfo);
	}
	
	/**
	 * Returns a chosen adjacent room
	 * @param direction The direction of the exit
	 * @return The room the exit leads to
	 */
	public ExitReq getExit(String direction) {
		return exits.get(direction);
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
        for(Element listItems : elementsInRoom.values()) {
        	returnString += listItems.getDescription() + "\n";
        }
        return returnString;
    }
    
    /**
     *  @return A description of the room, including exits and items.
     */
    public String getCompleteDescription() {
    	StringBuilder sb = new StringBuilder("You are " + description + "\n");
    	if(!elementsInRoom.isEmpty()) {
    		sb.append("You see " + getItemString());
    	}
    	sb.append("\n" + getExitString() + "\n");
        return sb.toString();
    }
    
    /**
     * Checks whether item is in room
     * @param The item the player wants to check
     * @return The boolean value true if item is in room, return false if not
     */
    public boolean isItemInRoom(String roomItem) {
        return elementsInRoom.containsKey(roomItem);
    }
    
    /**
     * Returns the item asked for by the player
     * @param itemName The item the player wants to pick up
     * @return The item the player picked up
     */
    public Element getRoomItem(String itemName) {
        return elementsInRoom.get(itemName);
    }
    
    /**
     * Removes the item asked for by the player from the room
     * @param itemName The item to remove from the room
     */
    public void removeItemFromRoom(String itemName) {
    	elementsInRoom.remove(itemName);
    }
    
    /**
     * Adds an item to the room.
     * @param itemName The String name of the item to add to the room
     * @param item The object item to add to the room
     */
    public void addItem(Element item) {
    	elementsInRoom.put(item.getName(), item);
    }
}