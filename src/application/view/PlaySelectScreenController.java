package application.view;

import javafx.fxml.FXML;

/**
 * this controller handles to play screen
 * this screen asks the user what options to pick, e.g easy, hard, custom
 */
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
        _mainApp.initQuestionSelect();
    }


    @FXML
    public void handleHard(){
        _mainApp.initQuestionSelect();
    }


}
