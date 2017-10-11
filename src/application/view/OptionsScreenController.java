package application.view;

import javafx.fxml.FXML;

public class OptionsScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}
