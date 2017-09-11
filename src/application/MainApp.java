package application;

import java.io.IOException;

import application.view.StartMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage _primaryStage;
	private BorderPane _startMenu;
	private AnchorPane _stageSelect;
	
	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		_primaryStage.setTitle("Tatai!");
		
		initStartMenu();
	}

	public void initStartMenu(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("application/view/StartMenu.fxml"));
			_startMenu = (BorderPane) loader.load();
			
			// Show the scene containing the start menu
			Scene scene = new Scene(_startMenu);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void initStageSelect() {
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartMenuController.class.getResource("StageSelect.fxml"));
			_stageSelect = (AnchorPane) loader.load();
			
			// Show the scene containing the start menu
			Scene scene = new Scene(_stageSelect);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
