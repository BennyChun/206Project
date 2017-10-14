package application.view;

import application.model.EquationQuestion;

import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    private String mao="";

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
        alert.setHeaderText("Are you sure you want to leave?");
        alert.setContentText("All data of this session will be lost");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            _mainApp.initStartMenu();
        } else {

        }
    }
    //==========================================================================================
    @FXML
    public void handleRecord(){
        if (currentQuestionNumber <=11 ){ //THIS IS <= 9 BECAUSE THE LIST OF QUESTIONS GOES FROM 0 - 9

            Task<Void> recordTask = new Task<Void>() {
                @Override
                public Void call() {
                    listenButton.setDisable(true);
                    confirmButton.setDisable(true);
                    RecordingUtil record = new RecordingUtil();		//instantiates the Recording class so that we can use it's Utilities
                    record.recordVoice();							//record the users voice
                    record.convertVoiceToMaori();					//pass the users wav file to the KHT, and HTK will output the foo.mlf file
                    ReadHTKFile readRecout = new ReadHTKFile();		//instantiates the ReadHTKFile class
                    readRecout.readHTK();							//reads the foo.mlf file
                    mao = readRecout.getMaoriWords();		        //get the String of the maori word (this is the the users input answer)
                    return null;
                }
                @Override
                public void done() {
                    Platform.runLater(() -> {
                        listenButton.setDisable(false);
                        confirmButton.setDisable(false);
                    });
                }
            };
            new Thread(recordTask).start();
        }else {// all 10 questions has been answered
            System.out.println("you've finished the " + currentQuestionNumber + " questions!");//used for testing purposes
            //getResults();
        }




    }
    //==========================================================================================
    @FXML
    public void handleConfirm(){






    }
    //==========================================================================================
    @FXML
    public void handleListen(){
        Task<Void> playRecordingTask = new Task<Void>() {
            @Override
            public Void call() {
                confirmButton.setDisable(true);
                recordButton.setDisable(true);
                RecordingUtil play = new RecordingUtil();
                play.playRecording();
                return null;
            }
            @Override
            public void done() {
                Platform.runLater(() ->{
                    confirmButton.setDisable(false);
                    recordButton.setDisable(false);
                });
            }
        };
        new Thread(playRecordingTask).start();


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
