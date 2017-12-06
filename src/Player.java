import java.util.HashMap;
import java.util.Stack;

/**
 * The Player class stores information regarding the player.
 * The player is created when the user starts the game.
 * @author felix
 *
 */
public class Player {
	private String name;
	//This could also be an arrayList, they wouldn't function any differently.
	private HashMap<String, Item> inventory;
	private Room currentRoom;
	private Stack<Room> roomHistory;
	private Controller controller;
	
	/**
	 * Constructor for the Player, sets the name for the user.
	 * @param name The name of the player, used for leader boards.
	 */
	public Player(String name, Controller controller) {
		this.name = name;
		inventory = new HashMap<String, Item>();
		roomHistory = new Stack<Room>();
		this.controller = controller;
	}
	
	/**
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}
	
	//This method could be unnecessary.
	/**
	 * @return The inventory of the player
	 */
	public HashMap<String, Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Adds an item to the inventory
	 * @param item The item to be added
	 */
	public void setInventory(Item item) {
		inventory.put(item.getName(), item);
	}
	
	/**
	 * Returns an item from the inventory
	 * @param itemName The name of the item to be returned
	 * @return The item to be returned
	 */
	public Item getInventory(String itemName) {
		return inventory.get(itemName);
	}
	
	/**
	 * Checks if a given item exists in the player's inventory.
	 * @param item the name of the item to be checked.
	 * @return
	 */
	public boolean hasItem(Item item) {
		return inventory.containsValue(item);
	}
	   
    /**
     * Removes an item from inventory and prints out information.
     * @param invItem The item the player want to remove from inventory.
     */
    public void removeInventory(String invItem) {
      Element itemToDrop = inventory.remove(invItem);
      currentRoom.addItem(itemToDrop);
      controller.printGameInfo("You dropped " + itemToDrop.getName() + "." + "\n");
    }
    
    /**
     * Returns a String with all items currently in inventory.
     * @return A string with all items in inventory separated by space.
     */
    public String getInventoryString() {
      String returnString = "Inventory:";
      for(String key : inventory.keySet()) {
          returnString += "  " + key;
      }
      return returnString;
    }
    
	/**
	 * @return The current room of the player
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Changes the current room of the player
	 * @param currentRoom The current room of the player
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	/**
	 * @return The latest room the player visited
	 */
	public Room getRoomHistory() {
		return roomHistory.pop();
	}
	
	/**
	 * Adds a new room to the room history
	 */
	public void setRoomHistory() {
		roomHistory.push(currentRoom);
	}
	
	/**
	 * Checks whether the stack of rooms is empty.
	 * @return The boolean value true if stack is empty, returns false if it is not.
 	 */
  public boolean checkIfEmpty() {
      return roomHistory.empty();
  }
}