
public class GameSetup {
	Player player;
	
	/**
	 * The 
	 * @param player
	 */
	public GameSetup(Player player) {
		this.player = player;
	}
	
	/**
	 * Creates all the different rooms in the game and sets all exits.
	 */
	public void setUpGame() {
		Room platform, camp, techInSnow;
		
		platform = new Room("on a small platform sticking out from the mountain. There is a long way up" +
				" and even longer way down. Up is preferable.");
		camp = new Room("in a small encampment. There is a camp fire, small tent and a bunch of junk.");
		techInSnow = new Room("on snow-covered plain. There isn't much here except for a strange box half buried in the snow.");
	
		player.setCurrentRoom(platform);
		
		Item icepicks, snowpile;
		
		icepicks = new Item("ice picks", "two pair of ice picks, makes for a good grip on ice.", false, null, true);
		snowpile = new Item("snowpile", "a medium sized snowpile, the one you made when you fell down here.", true, icepicks, false);
		snowpile.setSearchedText("Searching the pile of snow, you find " + snowpile.getContains().getDescription());
		
		platform.addItem(snowpile);
		
		platform.setExit("up", camp, "You try to climb the icy wall,  only to lose your grip and fall down on the pile of snow",
		"With your ice picks, you scale the wall as if it was horizontal.",icepicks);
		camp.setExit("west", techInSnow, "", "", null);
		camp.setExit("down", platform, "", "", null);
		
	}
}
