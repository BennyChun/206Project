package application.util;

import java.io.IOException;

import application.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NavigatorUtil {

	private final Stage _stage;
	
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
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
