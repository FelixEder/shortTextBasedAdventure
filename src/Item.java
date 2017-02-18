/**
 * The items of the game, only stores strings with information regarding name and description.
 * @author felix
 *
 */
public class Item {
	private String name, description, searchedText;
	private boolean searchable, liftable;
	private SearchReq contains;
	
	/**
	 * Constructor, sets the two fields of the class
	 * @param name The name of the item
	 * @param description The description of the item
	 */
	public Item(String name, String description, boolean searchable, boolean liftable) {
		this.name = name;
		this.description = description;
		this.searchable = searchable;
		this.liftable = liftable;
	}
	
	/**
	 * Nested class that sets requirements for searching items
	 */
	public class SearchReq {
		public Item[] storedItems;
		public String failedSearch, successSearch;
		public Item itemNeeded;
		
		private SearchReq(Item[] storedItems, String failedSearch, String successSearch, Item itemNeeded) {
			this.storedItems = storedItems;
			this.failedSearch = failedSearch;
			this.successSearch = successSearch;
			this.itemNeeded = itemNeeded;
		}
	}
	
	/**
	 * Creates a searchReq for the item.
	 * @param storedItems The items stored in the item.
	 * @param failedSearch Text printed when failing to open this item.
	 * @param successSearch Text succeeding when failing to open item.
	 * @param itemNeeded The item needed to open this item.
	 */
	public void setSearchedReq(Item[] storedItems, String failedSearch, String successSearch, Item itemNeeded) {
		contains = new SearchReq(storedItems, failedSearch, successSearch, itemNeeded);
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
	//public Item getContains() {
	//	return contains;
	//}
	
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
