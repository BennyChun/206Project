package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StartMenuController extends AbstractController {
	
	public void handlePlay() {
		_mainApp.initStageSelect();
	}
	
	@FXML
	public void handleInstructiosn() {
		_mainApp.initInstructions();
	}
	
	@FXML
	public void handleStats() {
		
	}

	@FXML
	public void handleQuit() {
		_mainApp.close();
	}	

}
