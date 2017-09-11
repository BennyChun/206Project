package application.view;

import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StartMenuController {
	
	private NavigatorUtil _navigator;
	
	public StartMenuController(NavigatorUtil navigator) {
		_navigator = navigator;
	}
	
	@FXML
	public void handlePlay() {
		_navigator.goToStageSelect();
	}
	
	public void handleStats() {
		
	}

	public void handleQuit() {
		
	}	

}
