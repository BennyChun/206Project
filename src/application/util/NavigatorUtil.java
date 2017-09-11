package application.util;

import java.io.IOException;

import application.MainApp;
import application.view.StageSelectController;
import application.view.StartMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NavigatorUtil {

	private final Stage _stage;
	private NavigatorUtil _navigator;
	
	public NavigatorUtil(Stage stage) {
		_stage = stage;
	}
	
	public void goToStageSelect() {	
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StageSelect.fxml"));
			AnchorPane stageSelect;
			stageSelect = (AnchorPane) loader.load();
			
			// Show the scene containing the start menu
			Scene scene = new Scene(stageSelect);
			_stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
	}
	
	public void goToStartMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StartMenu.fxml"));
			loader.setController(new StageSelectController(_navigator));
			BorderPane startMenu = (BorderPane) loader.load();
			
			// Show the scene containing the start menu
			Scene scene = new Scene(startMenu);
			_stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
