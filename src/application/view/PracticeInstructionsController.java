package application.view;

import javafx.fxml.FXML;

public class PracticeInstructionsController extends AbstractController{
	
	@FXML
	public void handleContinue() {
		_mainApp.initLevelScreen("hard");
	}

	@FXML
	public void handleBack(){ _mainApp.initStartMenu(); }
	
}
