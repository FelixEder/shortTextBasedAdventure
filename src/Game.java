
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
	private void createRooms() {
		
	}
	
	/**
	 * Starts the infinite loop that is used when the game is running
	 */
	public void play() {
		printStartMessage();
		
		boolean done = false;
		while(!done) {
			Command command = parser.getCommand();
			done = processCommand(command);
		}
		System.out.println("Thanks for enjoying my little game, have a nice day.");
	}
	
	/**
	 * The text that is printed out in beginning of the game.
	 */
	private void printStartMessage() {
		
	}
	
	/**
	 * Processes the commands from the player
	 * @param command The command from the player
	 * @return The boolean regarding if the player wants to quit the game
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		enumCommands commandWord = command.getCommandWord();
		
		switch(commandWord) {
		//Add more cases as the number of commands increases.
		default:   
			System.out.println("I don't know what you mean..." + "\n");
            break;
		}
		return wantToQuit;
	}
}