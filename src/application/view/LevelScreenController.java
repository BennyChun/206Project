package application.view;

import javafx.fxml.FXML;

public class LevelScreenController extends AbstractController{
	
	@FXML
	public void handleMainMenu() {
		_mainApp.initStartMenu();
	}
	
	@FXML
	public void handleRecord() {
		
	}
	
	@FXML
	public void handleSkip() {
		
	}
	
	@FXML
	public void handleOpenInfo() {
		System.out.println("hello");
	}
	
	@FXML
	public void handleCloseInfo() {
		System.out.println("bye");
	}
	
}
