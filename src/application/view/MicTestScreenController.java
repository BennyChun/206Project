package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MicTestScreenController extends AbstractController {

    @FXML private Button recordButton;
    @FXML private Button playButton;

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void handleRecord(){

    }

    @FXML
    public void handlePlay(){

    }


}
