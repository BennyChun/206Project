package application;
import java.io.File;
import java.io.IOException;

import application.model.EquationQuestion;
import application.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage _primaryStage;
	private AnchorPane _startMenu;
	
	@Override
	public void start(Stage primaryStage) {
		setUpSavedGamesStats();//sets up the SavedGamesStats directory
		setUpCustomGames();//sets yp the CustomGames directory
		_primaryStage = primaryStage;
		_primaryStage.setTitle("Tātai!");
		initStartMenu();
	}

	public void initStartMenu(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StartMenu.fxml"));
			_startMenu = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_startMenu);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			_primaryStage.sizeToScene();
			
			//give the start menu controller access to the MainApp
			StartMenuController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	/**
	 * initializes the PlaySelectScreen
	 * and passes the scene to the PlaySelectScreenController
	 *
	 */
	public void initPlaySelect(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlaySelectScreen.fxml"));
			AnchorPane _playSelect = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_playSelect);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);


			//give stage select controller access to the main app
			PlaySelectScreenController controller = loader.getController();
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
	public void initLevelScreen(String selectedLevel){// THIS MAKES THE PRACTICE SCREEEEEN
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PracticeLevelScreen.fxml"));
			AnchorPane _levelScreen = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(_levelScreen);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			PracticeLevelScreenController controller = loader.getController();
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
			loader.setLocation(MainApp.class.getResource("view/PracticeInstructions.fxml"));
			AnchorPane instructions = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(instructions);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			PracticeInstructionsController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
	public void initEndScreen(int finalScore , String currentLevel){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PracticeEndScreen.fxml"));
			AnchorPane endScreen = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(endScreen);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);
			
			//give stage select controller access to the main app
			PracticeEndScreenController controller = loader.getController();
			
			
			controller.setScoreLabel(finalScore);
			controller.setPreviousLevel(currentLevel);
			controller.disableHardButton(finalScore);
			controller.setMainApp(this);
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initAchievements(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AchievementsScreen.fxml"));
			AnchorPane achievements = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(achievements);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			AchievementsScreenController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initMic(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MicTestScreen.fxml"));
			AnchorPane micTest = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(micTest);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			MicTestScreenController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initCharacters(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CharacterPurchaseScreen.fxml"));
			AnchorPane chars = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(chars);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			CharacterPurchaseScreenController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initStatistics(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StatisticsScreen.fxml"));
			AnchorPane stats = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(stats);
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


	public void initCustom(){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CustomLevelScreen.fxml"));
			AnchorPane customLevel = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(customLevel);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			CustomLevelScreenController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * this method initializes the QuestionSlectionScreen (the screen with the plus, minus .... buttons)
	 * this method takes an argument of type string
	 * saying if the user selected either easy or hard
	 *
	 * easy level: will have all 4 operators, but the sum only goes from 1 to 20
	 * hard level: will have all 4 operators, but the sum only goes from 1 to 99
	 *
	 * @param selectedLevel
	 */
	public void initQuestionSelect(String selectedLevel){//either easy or hard
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/QuestionSelectScreen.fxml"));
			AnchorPane questionSelect = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(questionSelect);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);


			//give stage select controller access to the main app
			QuestionSelectScreenController controller = loader.getController();
			controller.setLevel(selectedLevel);
			controller.setMainApp(this);


		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * This initialises the main game levels, takes a difficulty and a multiplication level
	 */
	public void initMainLevelScreen(String selectedLevel, String selectedOperation){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LevelScreen.fxml"));
			AnchorPane questionSelect = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(questionSelect);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			LevelScreenController controller = loader.getController();

			ObservableList<EquationQuestion> LOL = FXCollections.observableArrayList();
			for (int i = 0 ; i < 10 ; i++) {
				EquationQuestion temp = new EquationQuestion(selectedLevel, selectedOperation);
				LOL.add(temp);

				//testing purposes
				System.out.print(temp.getTheEquation() + " = ");
				System.out.println(temp.getTheAnswer());
			}
			controller.setDifficultyAndMode(selectedLevel,selectedOperation);

			controller.setList(LOL);
			controller.showCurrentQuestion();



			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * this method take an int of the final score
	 * string of the selectedlevel
	 * string of the selected operation
	 *
	 * it will then open the EndScreen.fxml
	 * and pass the 3 parameters to the controller (EndScreenController)
	 * @param finalScore
	 * @param selectedLevel
	 * @param selectedOperation
	 */
	public void initMainEndScreen(int finalScore , String selectedLevel, String selectedOperation){
		try {
			//load start menu from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EndScreen.fxml"));
			AnchorPane endScreen = (AnchorPane) loader.load();

			// Show the scene containing the start menu
			Scene scene = new Scene(endScreen);
			_primaryStage.setScene(scene);
			_primaryStage.show();
			_primaryStage.setResizable(false);

			//give stage select controller access to the main app
			EndScreenController controller = loader.getController();

			controller.setScoreLabel(finalScore);
			controller.setPreviousLevel(selectedLevel, selectedOperation);
			controller.setMainApp(this);


		}catch(IOException e){
			e.printStackTrace();
		}
	}


	//==================================================

	/**
	 * when yoy call this method, it will get the current working directory
	 * it will check whether the directory: SavedGamesStats exists
	 * it will then create the directory if it doesn't exits
	 */
	public void setUpSavedGamesStats(){
		File theDir = new File(System.getProperty("user.dir")+"/SavedGamesStats");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());

			try{
				theDir.mkdir();
			}
			catch(Exception eee){
				eee.printStackTrace();
			}
		}else{
			//the directory already exits, so do nothing ?
			//System.out.println(System.getProperty("user.dir")+"/SavedGames" + ": already exists !");
		}
	}

	/**
	 * when you cal lthis method
	 * it will check if the directory: CustomGames exists
	 * if it doesn't exist, make the directory.
	 */
	public void setUpCustomGames(){
		File theDir = new File(System.getProperty("user.dir")+"/CustomGames");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());

			try{
				theDir.mkdir();
			}
			catch(Exception eee){
				eee.printStackTrace();
			}
		}else{
			//the directory already exits, so do nothing ?
			//System.out.println(System.getProperty("user.dir")+"/CustomGames" + ": already exists !");
		}
	}
	//==================================================


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
