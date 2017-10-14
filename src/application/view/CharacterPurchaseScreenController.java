package application.view;

import javafx.fxml.FXML;

public class CharacterPurchaseScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}
