package application;
import java.io.IOException;

import application.util.NavigatorUtil;
import application.view.StageSelectController;
import application.view.StartMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage _primaryStage;
	private NavigatorUtil _navigator;
	private BorderPane _startMenu;
	
	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		_primaryStage.setTitle("Tatai!");
		_navigator = new NavigatorUtil(primaryStage);
		initStartMenu();
	}

	public void initStartMenu(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StartMenu.fxml"));
			loader.setController(new StartMenuController(_navigator));
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
