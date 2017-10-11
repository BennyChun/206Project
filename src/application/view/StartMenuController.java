package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;

public class StartMenuController extends AbstractController {
	
	
	/**
	 * This method gets called when the user clicks on the practice button
	 * will give users instructions before continuing to practice stage
	 */
	@FXML
	public void handlePractice() {
		_mainApp.initInstructions();
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

	@FXML
	public void handleStatistics(){
		_mainApp.initStatistics();
	}

	@FXML
	public void handleCharacter(){
		_mainApp.initCharacters();
	}

	@FXML
	public void handleAchievements(){
		_mainApp.initAchievements();
	}

	@FXML
	public void handleOptions(){
		_mainApp.initOptions();
	}

}
