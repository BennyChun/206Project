package application.view;

import application.model.EquationQuestion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class LevelScreenController extends AbstractController {

    private ObservableList<EquationQuestion> lol;

    /**
     * this constructor takes an observableList of EquationQuestion objects
     * @param lol
     */
    public void setList(ObservableList<EquationQuestion> lol){
        this.lol = lol;
    }



    //=================================== MIGHT WANT TO CHANGE DIALOG ========================================
    @FXML
    public void handleBack(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("ALL DATA WILL BE LOST !");
        alert.setContentText("ARE U SURE ????!?!?!? BRU?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            _mainApp.initStartMenu();
        } else {

        }
    }
    //==========================================================================================
    @FXML
    public void handleRecord(){





    }
    //==========================================================================================
    @FXML
    public void handleConfirm(){






    }
    //==========================================================================================
    @FXML
    public void handleListen(){






    }
    //==========================================================================================
}
