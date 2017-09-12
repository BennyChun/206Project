package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StageSelectController extends AbstractController {
	
	
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
		_mainApp.initStartMenu();
	}
	
}
