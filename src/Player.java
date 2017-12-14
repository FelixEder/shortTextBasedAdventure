import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

/**
 * The Player class stores information regarding the player.
 * The player is created when the user starts the game.
 * @author Felix Eder
 *
 */
public class Player {
	private String name;
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
		inventory = new HashMap<>();
		roomHistory = new Stack<>();
		this.controller = controller;
	}
	
	/**
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

  /**
   * @return the names of all the items in the inventory in a set
   */
  public Set<String> getInventoryNames() {return inventory.keySet();}
	
	/**
	 * Adds an item to the inventory
	 * @param item The item to be added
	 */
	public void addItemToInventory(Item item) {
		inventory.put(item.getName(), item);
    controller.updateInventoryText(inventory.keySet());
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
	 * @return True if the inventory contains the specific item and vice versa.
	 */
	public boolean hasItem(Item item) {
		return inventory.containsValue(item);
	}
	   
  /**
   * Removes an item from inventory and prints out information.
   * @param invItem The item the player want to remove from inventory.
   */
  public void removeItemFromInventory(String invItem) {
    Element itemToDrop = inventory.remove(invItem);
    currentRoom.addItem(itemToDrop);
    controller.printGameInfo("You dropped " + itemToDrop.getName() + "." + "\n");
    controller.updateInventoryText(inventory.keySet());
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