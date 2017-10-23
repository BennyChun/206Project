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


    //this controller should also try and populate the ListView with all custom made equations



    @FXML
    public void handleBack(){
        _mainApp.initPlaySelect();
    }

    @FXML
    public void handleAdd(){
        //should open up a new scene, where the user cna make their equation, and submit
    }

    @FXML
    public void handlePlay(){
        //check if the user clicked on a equationlist
        //if they have, pass that equation list to the LevelScreenController, and load the FXML for that
    }

    @FXML
    public void handleDelete(){
        //check if the user selected an item on the view
        //if they have, as if they want to delete, if they say yes
        //go to the CustomGames folder and delete it
    }

}
