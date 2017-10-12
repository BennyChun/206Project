package application.view;

import javafx.fxml.FXML;

public class PlaySelectScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void handleCustom(){
        _mainApp.initCustom();
    }

    @FXML
    public void handleEasy(){

    }

    @FXML
    public void handleHard(){

    }


}
