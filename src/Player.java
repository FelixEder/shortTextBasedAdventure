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
	
	/**
	 * Constructor for the Player, sets the name for the user.
	 * @param name The name of the player, used for leader boards.
	 */
	public Player(String name) {
		this.name = name;
		inventory = new HashMap<String, Item>();
		roomHistory = new Stack<Room>();
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
	
	//Method in Item class will be added
	/**
	 * Adds an item to the inventory
	 * @param item The item to be added
	 */
	public void setInventory(Item item) {
		inventory.put(item.getName(), item);
	}
	
	/**
	 * Returns an item from the inventory
	 * @param item The item to be returned
	 * @return The item to be returned
	 */
	public Item getInventory(Item item) {
		return inventory.get(item.getName());
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
	 * @param room The latest room the player visited
	 */
	public void setRoomHistory(Room room) {
		roomHistory.push(room);
	}
}
