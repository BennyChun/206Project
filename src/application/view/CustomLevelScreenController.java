package application.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import static application.MainApp.mascotImage;

public class CustomLevelScreenController extends AbstractController{

    @FXML private ImageView mascot;

    @FXML
    private void initialize(){

        mascot.setImage(mascotImage);
    }

    @FXML
    public void handleBack(){
        _mainApp.initPlaySelect();
    }

    @FXML
    public void handleAdd(){

    }

    @FXML
    public void handlePlay(){

    }

    @FXML
    public void handleDelete(){

    }

}
