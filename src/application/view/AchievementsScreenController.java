package application.view;

import javafx.fxml.FXML;

public class AchievementsScreenController extends AbstractController{

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}