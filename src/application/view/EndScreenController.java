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
		String operation;

		if (selectedOperation.equals("addition")) {
			operation = "+";

		}else if (selectedOperation.equals("subtraction")){
			operation = "-";

		}else if (selectedOperation.equals("multiplication")){
			operation = "*";
		}else {
			operation = "/";

		}
		_mainApp.initMainLevelScreen(selectedLevel, operation);
	}

	/**
	 * this method takes a selectedLevel (String)
	 * and takes a selectedOperation (String)
	 *
	 * it will store those input strings as a global variable
	 * @param selectedLevel
	 * @param selectedOperation
	 */
	public void setPreviousLevel(String selectedLevel , String selectedOperation) {
		this.selectedLevel = selectedLevel;
		this.selectedOperation = selectedOperation;

	}

	public void setScoreLabel(int finalScore) {
		score.setText(Integer.toString(finalScore)+"/10");
	}



}
