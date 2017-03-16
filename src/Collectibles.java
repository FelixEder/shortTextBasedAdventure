/**
 * Collectibles are similar to items, they can be picked up by the player
 * but are then promptly removed from the game.
 * @author felix
 *
 */
public class Collectibles extends Element {
	private String pickUpText;

	public Collectibles(String description, String name, String pickUpText) {
		super.description = description;
		super.name = name;
		this.pickUpText = pickUpText;
	}
	
	/**
	 * @return the pickUpText.
	 */
	public String getPickUpText() {
		return pickUpText;
	}
}