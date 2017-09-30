package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndScreenController extends AbstractController{
	
	
	private String previousLevel;
	
	@FXML
	private Label score;
	
	@FXML
	private Button hard;
	
	@FXML
	public void handleMainMenu() {
		_mainApp.initStartMenu();
	}
	
	@FXML
	public void handleHard() {
		_mainApp.initLevelScreen("hard");
	}
	
	@FXML 
	public void handleRetry() {
<<<<<<< HEAD
		_mainApp.initLevelScreen("easy");
=======
		if (previousLevel.equals("hard")) {
			_mainApp.initLevelScreen("hard");
		}else {
			_mainApp.initLevelScreen("easy");
		}
	}
	
	public void setPreviousLevel(String previousLevel) {
		this.previousLevel = previousLevel;
	}
	
	public void setScoreLabel(int finalScore) {
		score.setText(Integer.toString(finalScore));
	}
	
	public void disableHardButton(int finalScore) {
		if (finalScore >=8) {
			hard.setDisable(false);
		}else {
			hard.setDisable(true);
		}
>>>>>>> 058bb7bedddf58b13fd4e5a3fb13d39059bae1f1
	}

}
