package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StageSelectController extends AbstractController {
	
	
	@FXML
	public void handleEasy() {
		//the person pressed easy
		//should pass a argument to initLevelScreen
		//saying that the person selected easy level
		//so that initLevelScreen can generate the questions
		//based on what the user selected
		String selectedLevel = "easy";
		_mainApp.initLevelScreen(selectedLevel);
	}
	
	@FXML
	public void handleHard() {
		//the person pressed hard
		//should pass a argument to initLevelScreen
		//saying that the person selected easy level
		//so that initLevelScreen can generate the questions
		//based on what the user selected
		String selectedLevel = "hard";
		_mainApp.initLevelScreen(selectedLevel);
	}
	
	@FXML
	public void handleStageSelectBack() {
		//this will go back to the main menu
		_mainApp.initStartMenu();
	}
	
}
