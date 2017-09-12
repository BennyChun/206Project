package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StageSelectController extends AbstractController {
	
	/*
	private NavigatorUtil _navigator;
	public StageSelectController(NavigatorUtil navigator) {
		_navigator = navigator;
	}*/
	
	@FXML
	public void handleEasy() {
		_mainApp.initLevelScreen();
	}
	
	@FXML
	public void handleHard() {
		_mainApp.initLevelScreen();
	}
	
	@FXML
	public void handleStageSelectBack() {
		//_navigator.goToStartMenu();
		_mainApp.initStartMenu();
	}
	
}
