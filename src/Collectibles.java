/**
 * Collectibles are similar to items, they can be picked up by the player
 * but are then promptly removed from the game.
 * @author felix
 *
 */
public class Collectibles extends Element {
	private String pickUpText;

	public Collectibles(String description, String pickUpText) {
		super.description = description;
		this.pickUpText = pickUpText;
	}
	
	public String getPickUpText() {
		return pickUpText;
	}

}
