
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	protected static TextArea output;
	protected static TextField input;
	
	protected static List<String> history;
	protected static int historyPointer;
	protected static String textToRead = null;
	
	//static Stage classStage = new Stage();
	
	private Service<Void> backgroundThread;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	public void start(Stage stage) throws Exception {
		output = new TextArea();
		input = new TextField();
		history = new ArrayList<>();
		historyPointer = 0;
	
		output.setEditable(false);
		input.requestFocus();
		
		BorderPane.setAlignment(output, Pos.CENTER);
		BorderPane.setAlignment(input, Pos.BOTTOM_CENTER); //Can be changed when needed
		
		BorderPane base = new BorderPane(output, null, null, input, null); //BorderPane(Node center, Node top, Node right, Node bottom, Node left)
		
		input.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case ENTER: String text = input.getText();
							output.appendText(text + System.lineSeparator());
							history.add(text);
							historyPointer = history.size(); 
							input.clear();
							textToRead = text;
							break;
							
				case UP :	if(historyPointer == 0) break;
							historyPointer--;
							input.setText(history.get(historyPointer));
							input.selectAll();
							input.selectEnd(); //Does not change anything seemingly
							break;
							
				case DOWN:	if(historyPointer == history.size()-1) break;
							historyPointer++;
							input.setText(history.get(historyPointer));
							input.selectAll();
							input.selectEnd(); //Does not change anything seemingly
							break;

				default: break;
				}

			}
		});

		//Settings for the borderPane, can be changed later
		base.setPrefSize(800, 800);
		base.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
		
		Scene scene = new Scene(base);
		stage.setScene(scene);
		stage.setTitle("The Farmhouse");
		stage.show();
		
		//Starts a new Javafx thread and launches the game on it.
		backgroundThread = new Service<Void>() {
		
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
						Game game = new Game("English", "PlayerName");
						game.play();
						return null;
					}
				};
			}
		};
		backgroundThread.restart();
		//When the game is completed on the other thread, this is called.
		backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				try {
					Platform.exit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * A toggle to make the input field editable or non-editable.
	 * @param option The answer to the question, "should the input field be set to editable?".
	 */
	public static void setInputEditable(boolean option) {
		input.setEditable(option);
	}
	
	/**
	 * Called when the game wants to print something to the game
	 * @param message The text to be printed to the console.
	 */
	public static void printGameInfo(String message) {
		output.appendText(message + System.lineSeparator());
	}
	
	/**
	 * Waits until the field textToRead is non-null and
	 * returns it, setting the field to null afterwards.
	 * @return The current text value in the String field.
	 * @throws InterruptedException 
	 */
	public static String getTextField() {
		while(textToRead == null) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		String returnText = textToRead;
		textToRead = null;
		return returnText;
	}
}
