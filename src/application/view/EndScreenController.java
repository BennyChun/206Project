package application.view;

import javafx.fxml.FXML;

public class EndScreenController extends AbstractController{
	
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
		_mainApp.initLevelScreen("easy");
	}

}
