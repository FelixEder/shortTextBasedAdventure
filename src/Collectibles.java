/**
 * Collectibles are similar to items, they can be picked up by the player
 * but are then promptly removed from the game.
 * @author felix
 *
 */
public class Collectibles extends Element {
	//The text printed when the player sees an collectible in a room.
	private String pickUpText;
	
	public Collectibles(String name, String description, String pickUpText) {
		super.name = name;
		super.description = description;
		this.pickUpText = pickUpText;
	}
	
	/**
	 * @return the pickUpText text.
	 */
	public String getpickUpTextText() {
		return pickUpText;
	}
}