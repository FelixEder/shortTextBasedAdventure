/**
 * The items of the game, only stores strings with information regarding name and description.
 * @author felix
 *
 */
public class Item {
	private String name, description, searchedText;
	private boolean searchable, liftable;
	private Item contains;
	
	/**
	 * Constructor, sets the two fields of the class
	 * @param name The name of the item
	 * @param description The description of the item
	 */
	public Item(String name, String description, boolean searchable, Item contains, boolean liftable) {
		this.name = name;
		this.description = description;
		this.searchable = searchable;
		this.contains = contains;
		this.liftable = liftable;
	}

	public void setSearchedText(String content) {
		searchedText = content;
	}
	
	public String getSearchedText() {
		return searchedText;
	}
	
	/**
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return if the item is searchable.
	 */
	public boolean isSearchable() {
		return searchable;
	}
	
	/**
	 * @return The item contained.
	 */
	public Item getContains() {
		return contains;
	}
	
	/**
	 * @return The description of the item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Boolean regarding if item is liftable or not.
	 */
	public boolean isLiftable() {
		return liftable;
	}
	
	/**
	 * Set an item as liftable or not.
	 * @param liftable The boolean value regarding the question told above.
	 */
	public void setLiftable(Boolean liftable) {
		this.liftable = liftable;
	}
}
