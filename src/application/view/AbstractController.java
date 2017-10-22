package application.view;

import application.MainApp;
import javafx.stage.Stage;

public abstract class AbstractController {
	
	
	// Reference to the main application.
	protected MainApp _mainApp;
	protected Stage _primaryStage;
	
	
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
	public void setMainApp(MainApp mainApp) {
		_mainApp = mainApp;
	}

	public void setPrimaryStage(Stage primaryStage){ _primaryStage = primaryStage; }
}
