package application.view;

//import application.util.NavigatorUtil;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;

import java.net.URISyntaxException;

import static application.MainApp.mascotImage;
import static org.controlsfx.control.PopOver.ArrowLocation.BOTTOM_RIGHT;
import static org.controlsfx.control.PopOver.ArrowLocation.TOP_CENTER;

public class StartMenuController extends AbstractController {

	@FXML private ImageView mascot;
	@FXML private Button charButton;
	private PopOver clickHerePopOver = new PopOver();

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

	@FXML
	public void showClickMe(){
		setPopOver();
	}

	@FXML
	public void closeClickMe(){
		clickHerePopOver.hide();
	}

	private void setPopOver(){
		String text = "Click Me!";
		Label help = new Label(text);
		help.setFont(new Font("Maiandra GD",40));
		clickHerePopOver.setContentNode(help);
		clickHerePopOver.setArrowLocation(BOTTOM_RIGHT);
		clickHerePopOver.setArrowSize(10);
		clickHerePopOver.setAutoHide(false);
		// remove min height property.
		DoubleProperty minHeight = clickHerePopOver.getRoot().minHeightProperty();
		minHeight.unbind();
		minHeight.set(0);
		// Set padding
		clickHerePopOver.getRoot().setPadding(new Insets(8));
		clickHerePopOver.show(mascot);
	}

}
