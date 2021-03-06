package controller_menus;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TutorialSelectMenuController extends DungeonSelectMenuController {
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button tutorial01Button;
	@FXML
	private Button tutorial02Button;
	@FXML
	private Button tutorial03Button;
	@FXML
	private Button tutorial04Button;
	@FXML
	private Button tutorial05Button;
	@FXML
	private Button tutorial06Button;
	@FXML
	private Button tutorial07Button;
	@FXML
	private Button tutorial08Button;
	@FXML
	private Button tutorial09Button;
	@FXML
	private Button tutorial10Button;
	
	private Button[][] buttons;
	
	private String[][] files = {
			{ "01.txt", "02.txt", "03.txt", "04.txt", "05.txt" },
			{ "06.txt", "07.txt", "08.txt", "09.txt", "10.txt" }
	};
	
	private int height  = 2;
	private int width   = 5;
	
	private int cursorX = 0;
	private int cursorY = 0;
	
	public TutorialSelectMenuController(Stage s, MenuHandler menus) {
		super(s, menus);
		
		cursorX = 0;
		cursorY = 0;
	}
	
	@FXML
	private void initialize() {
		display.addEventFilter(MouseEvent.ANY, e -> {
			e.consume();
		});
		
		Button[][] buttons = {
				{ tutorial01Button, tutorial02Button, tutorial03Button, tutorial04Button, tutorial05Button },
				{ tutorial06Button, tutorial07Button, tutorial08Button, tutorial09Button, tutorial10Button }
		};
		this.buttons = buttons;
	}
	
	@FXML
	private void handleKeyPress(KeyEvent e) {
		e.consume();
		KeyCode k = e.getCode();
		if (k.equals(KeyCode.ESCAPE)) {
			goToPreviousScreen();
			
		} else if (k.equals(KeyCode.ENTER)) {
			handler.switchToPlayingDungeon(
					"./src/game_files/levels/tutorial/" +
					files[cursorY][cursorX]);
			
		} else if (k.equals(KeyCode.UP)) {
			moveCursorUp();
		} else if (k.equals(KeyCode.DOWN)) {
			moveCursorDown();
		} else if (k.equals(KeyCode.RIGHT)) {
			moveCursorRight();
		} else if (k.equals(KeyCode.LEFT)) {
			moveCursorLeft();
		}
	}
	
	public boolean hasNextLevel() {
		return !(cursorX == (width - 1) &&
				 cursorY == (height - 1));
	}
	
	public String nextLevel() {
		cursorX++;
		if (cursorX == width) {
			cursorX = 0;
			cursorY++;
		}
		buttons[cursorY][cursorX].requestFocus();
		return "./src/game_files/levels/tutorial/" +
		       files[cursorY][cursorX];
	}
	
	private void moveCursorUp() {
		cursorY--;
		if (cursorY < 0) {
			cursorY += height;
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorDown() {
		cursorY++;
		if (cursorY >= height) {
			cursorY = (cursorY % height);
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorRight() {
		cursorX++;
		if (cursorX >= width) {
			cursorX = (cursorX % width);
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void moveCursorLeft() {
		cursorX--;
		if (cursorX < 0) {
			cursorX += width;
		}
		buttons[cursorY][cursorX].requestFocus();
	}
	
	private void goToPreviousScreen() {
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0.0);
		fadeOut.play();
		fadeOut.setOnFinished(e -> {
			handler.switchToZoneSelectMenu();
			display.setOpacity(1.0);
		});
	}
}
