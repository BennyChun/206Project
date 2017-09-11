package application.view;

import application.MainApp;

public abstract class AbstractController {
	
	private MainApp _mainApp;
	
	public void getMainApp(MainApp mainApp) {
		_mainApp = mainApp;
	}
}
