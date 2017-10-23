package application.view;

//import application.util.NavigatorUtil;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

import static application.MainApp.mascotImage;

public class StartMenuController extends AbstractController {

	@FXML private ImageView mascot;

	@FXML
	public void initialize(){
		// TODO change this because we need to set it from reading a file storing user's previous choice
		String path = null;
		try {
			path = this.getClass().getResource("kiwi.png").toURI().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mascotImage = new Image(path);
		mascot.setImage(mascotImage);
	}
	
	/**
	 * This method gets called when the user clicks on the practice button
	 * will give users instructions before continuing to practice stage
	 */
	@FXML
	public void handlePractice() {
		_mainApp.initInstructions();
	}	
	
	@FXML
	public void handleQuit() {
		_mainApp.close();
	}

	@FXML
	public void handlePlay(){
		_mainApp.initPlaySelect();//initialises the PlaySelectScreen.fxml and PlaySelectScreeenController
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
	public void handleMic(){
		_mainApp.initMic();
	}

}
