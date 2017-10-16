package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndScreenController extends AbstractController{


	private String selectedLevel;
	private String selectedOperation;

	@FXML
	private Label score;

	@FXML
	public void handleMainMenu() {
		_mainApp.initStartMenu();
	}

	@FXML 
	public void handleRetry() {
		_mainApp.initMainLevelScreen(selectedLevel, selectedOperation);
	}

	public void setPreviousLevel(String selectedLevel , String selectedOperation) {
		this.selectedLevel = selectedLevel;
		this.selectedOperation = selectedOperation;
		System.out.println(selectedLevel);
		System.out.println(selectedOperation);
	}

	public void setScoreLabel(int finalScore) {
		score.setText(Integer.toString(finalScore)+"/10");
	}



}
