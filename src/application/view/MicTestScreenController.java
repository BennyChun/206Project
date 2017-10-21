package application.view;

import javafx.fxml.FXML;

public class MicTestScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}
