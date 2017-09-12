package application.view;

import javafx.fxml.FXML;

public class InstructionsController extends AbstractController{
	
	@FXML
	public void handleContinue() {
		_mainApp.initStartMenu();
	}
	
}
