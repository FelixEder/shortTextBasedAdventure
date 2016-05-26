/**
 * The items of the game, only stores strings with information regarding name and description.
 * @author felix
 *
 */
public class Item {
	private String name, description;
	
	/**
	 * Constructor, sets the two fields of the class
	 * @param name The name of the item
	 * @param description The description of the item
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The description of the item
	 */
	public String getDescription() {
		return description;
	}
}
