package application.view;

import javafx.fxml.FXML;

public class PlaySelectScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }


}
