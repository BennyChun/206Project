package application.view;

import javafx.fxml.FXML;

public class StatisticsScreenController extends AbstractController {

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }
}
