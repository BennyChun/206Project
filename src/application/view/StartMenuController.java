package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StartMenuController extends AbstractController {
	
	
	/**
	 * This method gets called when the user clicks on the Play button
	 * This method will call the initStageSelect() method in the MainApp class
	 */
	public void handlePractice() {
		_mainApp.initLevelScreen("hard");
	}	
	
	@FXML
	public void handleInstructions() {
		_mainApp.initInstructions();
	}
	

	@FXML
	public void handleQuit() {
		_mainApp.close();
	}

	@FXML
	public void handlePlay(){
		_mainApp.initPlaySelect();
	}

}
