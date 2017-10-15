package application.view;

import application.model.EquationQuestion;

import application.util.MaoriAnswerUtil;
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
    private EquationQuestion currentEquation; // keeps track of the current Equation
    private String correctAnswer;// records the correct answer, for the current equation

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

        currentEquation = equationList.get(currentQuestionNumber - 1);// gets the first euqation from the list

        questionLabel.setText(" What is " + currentEquation.getTheEquation() + " ? ");//displays the currentEquation
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

        MaoriAnswerUtil maoUtil = new MaoriAnswerUtil();

        //int correctNumber = currentQuestion.getInteger();
        int correctNumber = currentEquation.getTheAnswer(); //this returns the currentEquation's correct answer: int
        maoUtil.numberToMaori(correctNumber);
        correctAnswer = maoUtil.getMaoriWords();

        System.out.println("user answer: " + mao);//testing purposes
        System.out.println("correct answer: " + correctAnswer);//testing purposes

        /*
        If the answer is right, show the next button and hide the record, play and listen buttons
        and also turn the text box and speech bubbles green. Set the corresponding question
        and tracker to a green tick.
        If the answer is wrong and attempt is 1 or 2, then show try again and skip buttons, turn
        text box and bubbles red
        If the answer is wrong and attempt is 3, then show the next button, turn text box and bubbles
        red.
         */
        if(mao.equals(correctAnswer)) {

            //the user got the question correct
            processCorrect();

        } else if(!(mao.equals(correctAnswer))){

            if (currentEquation.getCurrentAttempts()<3 ) {

                //if the user still has attempts left
                processIncorrect();

            } else if(currentEquation.getCurrentAttempts()==3) {//well obviously it will be 2 if the previous if statement is less than 2

                processFinalIncorrect();
            }

        } else if(mao.equals("")) {
            //there was no input from the user
            //do nothing
        }





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
    @FXML
    public void handleSkip(){

    }

    //==========================================================================================
    @FXML
    public void handleRetry(){

    }

    //==========================================================================================
    @FXML
    public void handleNext() {

    }

    //==========================================================================================

    /**
     * this sets the label for the difficulty and the operator that they chose
     * @param selectedLevel
     * @param selectedOperation
     */
    public void setDifficultyAndMode(String selectedLevel, String selectedOperation) {
        difficultyLabel.setText(selectedLevel.substring(0,1).toUpperCase() + selectedLevel.substring(1));

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
    // process answer methods

    // if answer is correct
    private void processCorrect(){
        hideInitialButtons();
        nextButton.setVisible(true);
        nextButton.setDisable(false);
    }

    // if the attempt is between 1 and 2
    private void processIncorrect(){
        hideInitialButtons();
        retryButton.setVisible(true);
        retryButton.setDisable(false);
        skipButton.setVisible(true);
        skipButton.setDisable(false);
    }

    private void processFinalIncorrect(){
        hideInitialButtons();
    }

    //==============================================================================================
    //Helper methods
    private void hideInitialButtons(){
        listenButton.setVisible(false);
        recordButton.setVisible(false);
        confirmButton.setVisible(false);
    }

    private void showInitialButtons(){
        listenButton.setVisible(true);
        recordButton.setVisible(true);
        confirmButton.setVisible(true);
    }



}
