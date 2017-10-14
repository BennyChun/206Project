package application.view;

import application.model.EquationQuestion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Optional;



public class LevelScreenController extends AbstractController {

    //=================================@FXML=========================================

    @FXML private Label difficultyLabel;
    @FXML private Label modeLabel;
    @FXML private Label incorrectLabel;
    @FXML private Label correctLabel;
    @FXML private Label questionLabel;
    //===============================================================================
    //question tracker fields
    @FXML private ImageView questionOne;
    @FXML private ImageView questionTwo;
    @FXML private ImageView questionThree;
    @FXML private ImageView questionFour;
    @FXML private ImageView questionFive;
    @FXML private ImageView questionSix;
    @FXML private ImageView questionSeven;
    @FXML private ImageView questionEight;
    @FXML private ImageView questionNine;
    @FXML private ImageView questionTen;

    //===============================================================================
    //attempt tracking fields
    @FXML private Rectangle attemptOne;
    @FXML private Rectangle attemptTwo;
    @FXML private Rectangle attemptThree;

    //===============================================================================
    // button fields
    @FXML private Button recordButton;
    @FXML private Button listenButton;
    @FXML private Button confirmButton;
    @FXML private Button skipButton;
    @FXML private Button retryButton;
    @FXML private Button nextButton;


    //==============================================================================
    private int currentQuestionNumber;//keeps track of which question we are on

    //==============================================================================

    private ObservableList<EquationQuestion> equationList;

    /**
     * this constructor takes an observableList of EquationQuestion objects
     */
    public void setList(ObservableList<EquationQuestion> equationList){
        this.equationList = equationList;
    }

    //========================================================================================
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        //should initialize the all the labels on the level scene

        currentQuestionNumber = 1;//initially the current question number is 1

        incorrectLabel.setVisible(false);
        correctLabel.setVisible(false);
    }

    /**
     * this method should be called from the start
     * it should should get the first question from the list
     * and display the equation on the label
     */
    public void showCurrentQuestion(){

        EquationQuestion currentEquation = equationList.get(currentQuestionNumber - 1);

        questionLabel.setText(" What is " + currentEquation.getTheEquation() + "? ");
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
     * this sets the label for the difficulty and the operator that they chose
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
