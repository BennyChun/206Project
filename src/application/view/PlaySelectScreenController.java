package application.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import static application.MainApp.mascotImage;

/**
 * this controller handles to play screen
 * this screen asks the user what options to pick, e.g easy, hard, custom
 */
public class PlaySelectScreenController extends AbstractController {

    @FXML private ImageView mascot;

    @FXML
    public void initialize(){
        mascot.setImage(mascotImage);
    }

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }


    @FXML
    public void handleCustom(){
        _mainApp.initCustom();
    }



    //===================================================
    @FXML
    public void handleEasy(){
        _mainApp.initQuestionSelect("easy");
    }


    @FXML
    public void handleHard(){
        _mainApp.initQuestionSelect("hard");
    }
    //==================================================


}
