/**
 * Collectibles are similar to items, they can be picked up by the player
 * but are then promptly removed from the game.
 * @author felix
 *
 */
public class Collectibles extends Element {
	//The text printed when the player sees an collectible in a room.
	private String locationText;
	
	public Collectibles(String name, String description, String locationText) {
		super.name = name;
		super.description = description;
		this.locationText = locationText;
	}
	
	/**
	 * @return the location text.
	 */
	public String getlocationText() {
		return locationText;
	}
}