package application.view;

import javafx.fxml.FXML;

public class QuestionSelectScreenController extends AbstractController{

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

}
