package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CharacterPurchaseScreenController extends AbstractController {


    @FXML
    private AnchorPane storePane;


    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void initialize(){
        try {
            //load start menu from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/StorePanel.fxml"));
            ScrollPane storePanel = (ScrollPane) loader.load();

            // Show the scene containing the start menu

            storePane.getChildren().add(storePanel);
            //give stage select controller access to the main app
            StorePanelController controller = loader.getController();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
