package application;
import java.io.IOException;

import application.view.InstructionsController;
import application.view.LevelScreenController;
import application.view.StageSelectController;
import application.view.StartMenuController;
import application.view.StatisticsScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage _primaryStage;
	private BorderPane _startMenu;
	
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
			loader.setLocation(MainApp.class.getResource("view/StartMenu.fxml"));
			_startMenu = (BorderPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_startMenu);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give the start menu controller access to the MainApp
			StartMenuController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void initStageSelect(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StageSelect.fxml"));
			AnchorPane _stageSelect = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_stageSelect);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			StageSelectController controller = loader.getController();
			controller.setMainApp(this);//this will set the main app scene to the stage select scene
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * this method will get called by the StageSelectController,
	 * that controller will pass an argument (string) 
	 * which is either easy or hard for the level to be generated
	 * @param selectedLevel
	 */
	public void initLevelScreen(String selectedLevel){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LevelScreen.fxml"));
			AnchorPane _levelScreen = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_levelScreen);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			LevelScreenController controller = loader.getController();
			controller.setLevel(selectedLevel);//passes the selected level to the controller
			controller.setMainApp(this);//this will set the mainapp scene to the level controller scene
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void initInstructions(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Instructions.fxml"));
			//loader.setController(new StartMenuController(_navigator));
			BorderPane instructions = (BorderPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(instructions);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			InstructionsController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
	public void initStatsScreen(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StatisticsScreen.fxml"));
			//loader.setController(new StartMenuController(_navigator));
			AnchorPane statsScreen = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(statsScreen);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			StatisticsScreenController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * when this method gets called
	 * it closes the primaryStage
	 */
	public void close() {
		_primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
