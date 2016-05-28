
public class Game {
	private Parser parser;
	private Player player;
	private String language;
	
	/**
	 * Main method, creates the game and starts it.
	 * @param args The first word is the language to be played, the second is the name of the player
	 */
	public static void main(String[] args) {
		Game game = new Game(args[0], args[1]);
		game.play();

	}
	
	/**
	 * Constructor for game
	 * @param language The language to be played
	 * @param playerName The name of the game.
	 */
	public Game(String language, String playerName) {
		parser = new Parser();
		player = new Player(playerName);
		this.language = language;
		createRooms();
	}

	/**
	 * Creates all the different rooms in the game and sets all exits.
	 */
	public void createRooms() {
		
	}
	
	/**
	 * Starts the infinite loop that is used when the game is running
	 */
	public void play() {
		
	}
}
