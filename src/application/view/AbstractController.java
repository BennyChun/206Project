package application.view;

import application.MainApp;

public abstract class AbstractController {
	protected MainApp _mainApp;
	
	public void setMainApp(MainApp mainApp) {
		_mainApp = mainApp;
	}
}
