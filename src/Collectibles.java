/**
 * Collectibles are similar to items, they can be picked up by the player
 * but are then promptly removed from the game.
 * @author felix
 *
 */
public class Collectibles extends Element {

	public Collectibles(String name, String description) {
		super.name = name;
		super.description = description;
	}
}