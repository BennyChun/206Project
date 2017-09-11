package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StartMenuController extends AbstractController {
	
	/*
	private NavigatorUtil _navigator;
	public StartMenuController(NavigatorUtil navigator) {
		_navigator = navigator;
	}*/
	
	@FXML
	public void handlePlay() {
		//_navigator.goToStageSelect();
		_mainApp.initStageSelect();
	}
	
	@FXML
	public void handleStats() {
		
	}

	@FXML
	public void handleQuit() {
		
	}	

}
