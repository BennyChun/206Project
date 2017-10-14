package application.view;

import application.model.EquationQuestion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.util.Optional;

import static org.controlsfx.control.PopOver.ArrowLocation.TOP_LEFT;

public class LevelScreenController extends AbstractController {

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label modeLabel;


    //==============================================================================

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

    /**
     * this sets the label for the
     * @param selectedLevel
     * @param selectedOperation
     */
    public void setDifficultyAndMode(String selectedLevel, String selectedOperation) {
        difficultyLabel.setText(selectedLevel);

        if (selectedOperation.equals("+")) {
            modeLabel.setText("Addition");
        }else if (selectedOperation.equals("-")){
            modeLabel.setText("Subtraction");
        }else if (selectedOperation.equals("*")){
            modeLabel.setText("Multiplication");
        }else {
            modeLabel.setText("Division");
        }
    }

    //============================================================================================





}
