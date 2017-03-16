
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
		Room platform, camp, techInSnow, helicopter, snowStorm;
		
		platform = new Room("on a small platform sticking out from the mountain. There is a long way up" +
				" and even longer way down. Up is preferable.");
		camp = new Room("in a small encampment. There is a camp fire, small tent and a bunch of junk.");
		techInSnow = new Room("on snow-covered plain. You admire the distant mountains.");
	
		helicopter = new Room("next to a wreckage of a helicopter. The rotor blade has been completely smashed.");
		
		//SnowStorm ska leda in lite i scenario 2, så vänta lite med denna
		snowStorm = new Room("part of scenario 2, will be worked on later");
		
		
		player.setCurrentRoom(platform);
		
		Item icepicks, snowpile, box, locker, key, map;
		Collectibles picture, audioLog;
		
		icepicks = new Item("icepicks", "A pair of ice picks, makes for a good grip on ice.", false, true);
		snowpile = new Item("snowpile", "a medium sized snowpile, the one you made when you fell down here.", true, false);	
		snowpile.setSearchedReq(new Item[] {icepicks}, "", "You stick your hands into the freezing snow and start searching, wondering why you would ever do such a  thing." + "\n"
		+ "Finally, you find:", null);
	
		key = new Item("key", "A small key.", false, true);
		picture = new Collectibles("picture", "A picture, on it you see three men with big smiles." + "\n"
				+ "It feels eerily familiar.");
		box = new Item("box", "a strange box with a rusty lock, half buried in the snow.", true, false);
		box.setSearchedReq(new Element[] {key, picture}, "You try to open the box with your arms, but the lock wont open.", 
				"You smash the lock with your ice picks and the box opens." + "\n" +  "Inside you find: ", icepicks);
		
		map = new Item("map", "A laminated high-quality map of the surrounding area.", false, true);
		audioLog = new Collectibles("audioLog", "A little cassette. You put it in your trusy cassette player and you listen to it:" + "\n" + "\n"
				+"\"Day three of the expedtion. We are closing in on the spot, but the wheather keeps getting worse.\"" + "\n"
				+ "\"What have you done?! You lead us into a storm!\"" + "\n" + "\n"
				+ "You start to hear the sound of the helicopter's engines struggling." + "\n" + "\n" +
				"\"It's all your fault, we'll take over from here!\"" + "\n" + "\n" +
				 "A brawl breaks out, followed by men screaming and crashing sound." + "\n" + "\n" +
				"The audio log has ended, but you remain in deep thought after what you just heard.");
		locker = new Item("locker", "a sturdy locker inside the helicopter. Closed of course.", true, false);
		locker.setSearchedReq(new Element[] {map, audioLog},"You try your darndest to pry open the locker, but to no avail",
				"You use the key and the locker opens. Inside you find: ", key);
				
		platform.addItem(snowpile);
		techInSnow.addItem(box);
		helicopter.addItem(locker);
		
		platform.setExit("up", camp, "You try to climb the icy wall,  only to lose your grip and fall down on the pile of snow",
		"With your ice picks, you scale the wall as if it was horizontal.", icepicks);
		camp.setExit("west", techInSnow, "", "", null);
		camp.setExit("down", platform, "", "", null);
		camp.setExit("north", helicopter, "", "", null);
		
		techInSnow.setExit("east", camp, "", "", null);
		techInSnow.setExit("north", helicopter, "", "", null);
		techInSnow.setExit("northwest", snowStorm, "You enter a blizzard, wandering around blindly. You have no idea where" +
				" to go so you head back to the calm snow-covered plains", "You enter the blizzard with map in hand and with it" +
						"you seem to find your way through the terrible storm", map);
		
		helicopter.setExit("southeast", camp, "", "", null);
		helicopter.setExit("southwest", techInSnow, "", "", null);
		
		
		
		
	}
}
