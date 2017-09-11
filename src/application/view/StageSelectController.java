package application.view;

import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StageSelectController {
	
	private NavigatorUtil _navigator;
	
	public StageSelectController(NavigatorUtil navigator) {
		_navigator = navigator;
	}
	
	@FXML
	public void handleEasy() {
		
	}
	
	@FXML
	public void handleHard() {
		
	}
	
	@FXML
	public void handleStageSelectBack() {
		_navigator.goToStartMenu();
	}
	
}
