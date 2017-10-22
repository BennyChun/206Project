package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StatisticsScreenController extends AbstractController {

    @FXML
    private AnchorPane statsPane;

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void handleHard(){
        try {
            //load start menu from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EasyStats.fxml"));
            ScrollPane hard = (ScrollPane) loader.load();

            // Show the scene containing the start menu

            Scene scene = new Scene(hard);
            statsPane.getChildren().add(hard);
            //give stage select controller access to the main app
            //MicTestScreenController controller = loader.getController();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEasy(){
        try {
            //load start menu from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HardStats.fxml"));
            ScrollPane hard = (ScrollPane) loader.load();

            // Show the scene containing the start menu

            Scene scene = new Scene(hard);
            statsPane.getChildren().add(hard);
            //give stage select controller access to the main app
            //MicTestScreenController controller = loader.getController();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void handleCustom(){
        try {
            //load start menu from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CustomStats.fxml"));
            ScrollPane hard = (ScrollPane) loader.load();

            // Show the scene containing the start menu

            Scene scene = new Scene(hard);
            statsPane.getChildren().add(hard);
            //give stage select controller access to the main app
            //MicTestScreenController controller = loader.getController();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
