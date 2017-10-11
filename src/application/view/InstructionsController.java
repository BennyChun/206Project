package application.view;

import javafx.fxml.FXML;

public class InstructionsController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}
