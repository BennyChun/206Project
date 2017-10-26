package application.view;

import application.model.EquationQuestion;
import application.util.MaoriAnswerUtil;
import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import application.util.SaveGameHelper;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;

import java.net.URISyntaxException;
import java.util.Optional;

import static application.MainApp.mascotImage;
import static org.controlsfx.control.PopOver.ArrowLocation.TOP_CENTER;


public class LevelScreenController extends AbstractController {

    //=================================@FXML=========================================

    @FXML private Circle circleOne;  //the little speech bubbles
    @FXML private Circle circleTwo;  //the little speech bubbles
    @FXML private Circle circleThree;//the little speech bubbles

    @FXML private Label difficultyLabel;
    @FXML private Label modeLabel;
    @FXML private Label incorrectLabel;
    @FXML private Label correctLabel;
    @FXML private Label questionLabel;
    @FXML private HBox questionTracker;
    @FXML private HBox attemptTracker;
    @FXML private ImageView mascot;

    @FXML private ProgressBar progressBar;//the progress bar
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
    // pop overs
    private PopOver recordPopOver = new PopOver();
    private PopOver listenPopOver = new PopOver();
    private PopOver confirmPopOver = new PopOver();
    private PopOver questionTrackerPopOver = new PopOver();
    private PopOver attemptPopOver = new PopOver();
    private PopOver retryPopOver = new PopOver();
    private PopOver skipPopOver = new PopOver();
    private PopOver nextPopOver = new PopOver();

    //==============================================================================
    private int currentQuestionNumber;//keeps track of which question we are on
    private EquationQuestion currentEquation; // keeps track of the current Equation
    private String selectedLevel;
    private String selectedOperation;
    private String correctAnswer;// records the correct answer, for the current equation
    private int finalScore=0;
    private String mao="";//this stores the users recorded answer in maori
    private int points = 0; // user score tracker in point form

    //==============================================================================

    private ObservableList<EquationQuestion> equationList; // this will store the 10 equation questions, passed into it from the start

    /**
     * this method takes an observable list of equations. and stores it in this controller
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

        //this will set all the question tracking tick thingy all invisible
        //will need to set them up one by one during run time of the code
       /* questionOne.setOpacity(0);
        questionTwo.setOpacity(0);
        questionThree.setOpacity(0);
        questionFour.setOpacity(0);
        questionFive.setOpacity(0);
        questionSix.setOpacity(0);
        questionSeven.setOpacity(0);
        questionEight.setOpacity(0);
        questionNine.setOpacity(0);
        questionTen.setOpacity(0);*/


        currentQuestionNumber = 1;//initially the current question number is 1

        //THIS NOT NOT NECESSARY IF YOU DISABLED AND HID THE BUTTONS ON FXML SCENE BUILDER
        //im doing this for extra redundancy checking
        retryButton.setDisable(true);
        retryButton.setVisible(false);

        skipButton.setDisable(true);
        skipButton.setVisible(false);

        nextButton.setDisable(true);
        nextButton.setVisible(false);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        incorrectLabel.setVisible(false);
        correctLabel.setVisible(false);
        mascot.setImage(mascotImage);
    }

    /**
     * this method should be called from the start
     * it should should get the first question from the list
     * and display the equation on the label
     */
    public void showCurrentQuestion(){

        currentEquation = equationList.get(currentQuestionNumber - 1);// gets the first equation from the list

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
            //do nothing
        }
    }
    //==========================================================================================
    @FXML
    public void handleRecord(){

        //should start the progress bar as another thread ? maybe ?
        startProgressBar();

        //initialilly the currentQuestionNumber == 1
        //once you press record, it is == 1


        //the currentQuestionNumber will only increase after the user clicks nextButton
        if (currentQuestionNumber <=10 ){ //PLEASE CHECK THIS NUMBER !!!
            Task<Void> recordTask = new Task<Void>() {
                    @Override
                    public Void call() {
                        listenButton.setDisable(true);
                        confirmButton.setDisable(true);

                        RecordingUtil record = new RecordingUtil();        //instantiates the Recording class so that we can use it's Utilities
                        record.recordVoice();                            //record the users voice
                        record.convertVoiceToMaori();                    //pass the users wav file to the KHT, and HTK will output the foo.mlf file
                        ReadHTKFile readRecout = new ReadHTKFile();        //instantiates the ReadHTKFile class
                        readRecout.readHTK();                            //reads the foo.mlf file
                        mao = readRecout.getMaoriWords();                //get the String of the maori word (this is the the users input answer)
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
        //hide the progress bar, and rest back to 0
        progressBar.setProgress(0.0);
        progressBar.setOpacity(0);


        MaoriAnswerUtil maoUtil = new MaoriAnswerUtil();


        int correctNumber = currentEquation.getTheAnswer(); //this returns the currentEquation's correct answer: int
        maoUtil.numberToMaori(correctNumber);
        correctAnswer = maoUtil.getMaoriWords(); //saves the correct answer as a maori string

        System.out.println("user answer: " + mao);//testing purposes
        System.out.println("correct answer: " + correctAnswer);//testing purposes

        if(mao.equals(correctAnswer)) {

            //the user got the question correct
            processCorrect();

        } else if(!(mao.equals(correctAnswer))){

            if (currentEquation.getCurrentAttempts()<3 ) {

                //if the user still has attempts left
                processIncorrect();

            } else if(currentEquation.getCurrentAttempts()==3) {

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


    //======================================================================================================
    @FXML
    public void handleRetry(){
        //the only way the user can get to here is they still have attempts left, if attempts < 3

        //hide the retry button
        //disable the retry button
        //show the initial buttons
        //hide the incorrectLabel.setVisible(false);
        //unfill the 3 bubbles

        //currentQuestionNumber SHOULD NOT CHANGE
        //currentEquation SHOULD NOT CHANGE
        //currentEquation.setAttempts()

        //unhide the progress bar
        progressBar.setOpacity(1);

        nextButton.setDisable(true);
        nextButton.setVisible(false);

        skipButton.setVisible(false);
        skipButton.setDisable(true);

        retryButton.setVisible(false);
        retryButton.setDisable(true);

        incorrectLabel.setVisible(false);
        showInitialButtons();//shows the question label and enable the play, record, submit buttons
        listenButton.setDisable(true);//but we actually need to disable them
        confirmButton.setDisable(true);//disable these because the user wants to retry, so the user does not want to re-listen to th old recording


        unFill();
        currentEquation.setCurrentAttempts();//increment the attempts

        questionLabel.setText(" What is " + currentEquation.getTheEquation() + " ? ");//displays the currentEquation
    }


    @FXML
    public void handleNext() {
        //    hide next button
        //    show the record, play and listen buttons.
        //    showCurrentQuestion()
        //
        //    set THIS.question label be a green tick
        //    show the 3 boxes(attempt boxes)
        //    hide the correct bubble
        //    refill the colour of the 3 bubbles

        //unhide the progress bar
        progressBar.setOpacity(1);

        nextButton.setVisible(false);//hides the next button
        nextButton.setDisable(true);//disables the next button
        skipButton.setVisible(false);
        skipButton.setDisable(true);
        retryButton.setDisable(true);
        retryButton.setVisible(false);



        incorrectLabel.setVisible(false);//hies the incorrectLabel bubble
        correctLabel.setVisible(false);//hides the correct label
        showInitialButtons();//shows the question label and enable the play, record, submit buttons
        confirmButton.setDisable(true);
        listenButton.setDisable(true);



        unFill();//un-fills the colours of the 3 little bubbles
        attemptTwo.setFill(Color.WHITE);
        attemptThree.setFill(Color.WHITE);

        if (currentEquation.isCorrect()){
            updateCorrectQuestionTracker();
        }else{//they got this question wrong
            updateIncorrectQuestionTracker();
        }

        currentQuestionNumber+=1;//increment to the next question number

        //CHECK FOR INDEX ERRORS
        if (currentQuestionNumber <= 10) {
            currentEquation = equationList.get(currentQuestionNumber - 1);//updates the currentEquation to the next one in the list
            questionLabel.setText(" What is " + currentEquation.getTheEquation() + " ? ");//displays the currentEquation
        }else{
            displayEndScreen();
        }


    }


    @FXML
    public void handleSkip(){
        //  hide next button
        //  show the record, play and listen buttons.
        //  showCurrentQuestion()
        //
        //  set THIS.question label be a green tick
        //  show the 3 boxes(attempt boxes)
        //  hide the correct bubble
        //  refill the colour of the 3 bubbles
        //  increments the currentQuestionNumber count
        //  updates currentEquation
        //  updates the questionLabel

        //unhide the progress bar
        progressBar.setOpacity(1);

        skipButton.setVisible(false);//hides the next button
        skipButton.setDisable(true);//disables the next button


        showInitialButtons();//shows the question label and enable the play, record, submit buttons
        retryButton.setVisible(false);
        retryButton.setDisable(true);
        confirmButton.setDisable(true);

        listenButton.setDisable(true);


        correctLabel.setVisible(false);//hides the correctLabel bubble
        incorrectLabel.setVisible(false);

        unFill();//un-fills the colours of the 3 little bubbles
        attemptTwo.setFill(Color.WHITE);
        attemptThree.setFill(Color.WHITE);


        currentEquation.setCorrect(false);//the user got this question wrong, cus they skipped it
        updateIncorrectQuestionTracker();//puts a cross on the tracker
        currentQuestionNumber+=1;//increment to the next question number

        //CHECK FOR INDEX ERRORS
        if (currentQuestionNumber <= 10) {
            currentEquation = equationList.get(currentQuestionNumber - 1);//updates the currentEquation to the next one in the list
            questionLabel.setText(" What is " + currentEquation.getTheEquation() + " ? ");//displays the currentEquation
        }else{

            displayEndScreen();
        }
    }


    //======================================================================================================================
    // process answer methods
    //
    // if answer is correct
//    show the next button
//    hide the record, play and listen buttons.
//    Also turn the text box and speech bubbles green
//
//    set currentEquation.setCorrect(true);
//    Set the currentQuestionNumber and set the currentEquation
//    Set tracker to a green tick.
//
//    go to (nextButton)

    private void processCorrect(){
        hideInitialButtons();//hide the question label AND disable/hide the 3 buttons (listen, record , confirm)
        // make the 3 little speech circles green
        correctFill();
        // add a tick to tracker, shows up on the gui
        updateCorrectQuestionTracker();
        correctLabel.setVisible(true);//shows the correctLabel bubble
        correctLabel.setText(" Well Done! \n You Said: " +correctAnswer + " " + "\n Which means: " + currentEquation.getTheAnswer() + " ");
        nextButton.setVisible(true);
        nextButton.setDisable(false);
        currentEquation.setCorrect(true);//sets the state of the current question to be TRUE (user answer this question correctly

    }


    // if the attempt is between 1 and 2
    private void processIncorrect(){
        hideInitialButtons();//hide the question label AND disable/hide the 3 buttons (listen, record , confirm)
        nextButton.setVisible(false);//disables the next button
        nextButton.setDisable(true);//this is kinda retarded, the next button and skip button have basically the same functionality

        //fill the circles red
        incorrectFill();

        incorrectLabel.setVisible(true);
        incorrectLabel.setText(" Awww, not quite right \n You said : \n" + " " + mao + " ");

        //if it's the first attempt and they get it wrong, update 2nd attempt tracker
        if((currentEquation.getCurrentAttempts() == 1)){
            attemptTwo.setFill(Color.YELLOW);
        } else if( currentEquation.getCurrentAttempts() == 2){
            attemptThree.setFill(Color.YELLOW);
        }

        // show and enable the retry and skip buttons
        retryButton.setVisible(true);
        retryButton.setDisable(false);

        skipButton.setVisible(true);
        skipButton.setDisable(false);
    }

    private void processFinalIncorrect(){

        hideInitialButtons();
        retryButton.setDisable(true);
        retryButton.setVisible(false);

        skipButton.setVisible(false);
        skipButton.setDisable(true);

        nextButton.setVisible(true);
        nextButton.setDisable(false);
        incorrectFill();

        currentEquation.setCorrect(false);//the user got this question wrong :(

        incorrectLabel.setVisible(true);
        incorrectLabel.setText(" Aww, not quite right \n You said: " + mao + "\n The correct answer is: " + currentEquation.getTheAnswer() + "\n Let's try another question! ");

    }


    /**
     * this method will get called at the end of the 10 questions
     * it should display the results of the 10 equationQuestions
     */
    private void displayEndScreen() {
        hideInitialButtons();
        getFinalSCore();
        SaveGameHelper test = new SaveGameHelper(equationList , selectedLevel , selectedOperation , finalScore);

        _mainApp.initMainEndScreen(finalScore, selectedLevel , selectedOperation);
    }

    //---------------------------------------------------------------------------------------------------------
    //=========================================================================================================
    //                                          Helper methods
    //=========================================================================================================
    //---------------------------------------------------------------------------------------------------------

    /**
     * this method will
     * hide the question label (the label that shows the equation)
     * hide the listen button
     * hide the record button
     * hide the confirm button
     *
     * and disable all 3 of the buttons
     */
    private void hideInitialButtons(){
        questionLabel.setVisible(false);
        listenButton.setVisible(false);
        recordButton.setVisible(false);
        confirmButton.setVisible(false);

        listenButton.setDisable(true);
        recordButton.setDisable(true);
        confirmButton.setDisable(true);
    }

    /**
     * thi method will
     * show the question label (the label that shows the equation)
     * show the listen button
     * show the record button
     * show the confirm button
     *
     * and enable all 3 buttons
     */
    private void showInitialButtons(){
        questionLabel.setVisible(true);
        listenButton.setVisible(true);
        recordButton.setVisible(true);
        confirmButton.setVisible(true);

        listenButton.setDisable(false);
        recordButton.setDisable(false);
        confirmButton.setDisable(false);
    }




    //====================================================================================

    /**
     * this method trys and open the green tick png
     * it will set the tracker tick based on which currentQuestionNumber we are currently on
     * when this method was called adds a green tick and makes the imageview visible
     */
    private void updateCorrectQuestionTracker(){
        String path = null;
        try {
            path = this.getClass().getResource("green.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Image correctImage = new Image(path);



        if(currentQuestionNumber == 1){
            questionOne.setImage(correctImage);
            questionOne.setOpacity(1);
        } else if(currentQuestionNumber == 2){
            questionTwo.setImage(correctImage);
            questionTwo.setOpacity(1);
        } else if(currentQuestionNumber == 3){
            questionThree.setImage(correctImage);
            questionThree.setOpacity(1);
        } else if(currentQuestionNumber == 4){
            questionFour.setImage(correctImage);
            questionFour.setOpacity(1);
        } else if(currentQuestionNumber == 5){
            questionFive.setImage(correctImage);
            questionFive.setOpacity(1);
        } else if(currentQuestionNumber == 6){
            questionSix.setImage(correctImage);
            questionSix.setOpacity(1);
        } else if(currentQuestionNumber == 7){
            questionSeven.setImage(correctImage);
            questionSeven.setOpacity(1);
        } else if(currentQuestionNumber == 8){
            questionEight.setImage(correctImage);
            questionEight.setOpacity(1);
        } else if(currentQuestionNumber == 9){
            questionNine.setImage(correctImage);
            questionNine.setOpacity(1);
        } else if(currentQuestionNumber == 10) {
            questionTen.setImage(correctImage);
            questionTen.setOpacity(1);
        }
    }


    /**
     * when you call this method, it will try and get the red cross png
     * and depnding on which currentQuestionNumber we are on
     * it will set the image for that tracker to be incorrect
     */
    private void updateIncorrectQuestionTracker(){
        String path = null;
        try {
            path = this.getClass().getResource("red.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Image incorrectImage = new Image(path);



        if(currentQuestionNumber == 1){
            questionOne.setImage(incorrectImage);
            questionOne.setOpacity(1);
        } else if(currentQuestionNumber == 2){
            questionTwo.setImage(incorrectImage);
            questionTwo.setOpacity(1);
        } else if(currentQuestionNumber == 3){
            questionThree.setImage(incorrectImage);
            questionThree.setOpacity(1);
        } else if(currentQuestionNumber == 4){
            questionFour.setImage(incorrectImage);
            questionFour.setOpacity(1);
        } else if(currentQuestionNumber == 5){
            questionFive.setImage(incorrectImage);
            questionFive.setOpacity(1);
        } else if(currentQuestionNumber == 6){
            questionSix.setImage(incorrectImage);
            questionSix.setOpacity(1);
        } else if(currentQuestionNumber == 7){
            questionSeven.setImage(incorrectImage);
            questionSeven.setOpacity(1);
        } else if(currentQuestionNumber == 8){
            questionEight.setImage(incorrectImage);
            questionEight.setOpacity(1);
        } else if(currentQuestionNumber == 9){
            questionNine.setImage(incorrectImage);
            questionNine.setOpacity(1);
        } else if(currentQuestionNumber == 10) {
            questionTen.setImage(incorrectImage);
            questionTen.setOpacity(1);
        }
    }



    //===========================================================================================================

    /**
     * this fills the colours of the 3 little bubbles
     */
    private void incorrectFill(){
        circleOne.setFill(Color.RED);
        circleTwo.setFill(Color.RED);
        circleThree.setFill(Color.RED);
    }

    /**
     * this fills the colours of the 3 little bubbles
     */
    private void correctFill(){
        circleOne.setFill(Color.GREENYELLOW);
        circleTwo.setFill(Color.GREENYELLOW);
        circleThree.setFill(Color.GREENYELLOW);
    }

    /**
     * this unfills the colours of the 3 little bubbles
     */
    private void unFill(){
        circleOne.setFill(Color.WHITE);
        circleTwo.setFill(Color.WHITE);
        circleThree.setFill(Color.WHITE);
    }
    //==========================================================================================

    /**
     * this sets the label for the difficulty and the operator that they chose
     * @param selectedLevel
     * @param selectedOperation
     */
    public void setDifficultyAndMode(String selectedLevel, String selectedOperation) {
        difficultyLabel.setText(selectedLevel.substring(0,1).toUpperCase() + selectedLevel.substring(1));
        this.selectedLevel = selectedLevel;


        if (selectedOperation.equals("+")) {
            modeLabel.setText("Addition");
            this.selectedOperation = "addition";
        }else if (selectedOperation.equals("-")){
            modeLabel.setText("Subtraction");
            this.selectedOperation = "subtraction";
        }else if (selectedOperation.equals("*")){
            modeLabel.setText("Multiplication");
            this.selectedOperation = "multiplication";
        }else {
            modeLabel.setText("Division");
            this.selectedOperation = "division";
        }
    }

    /**
     * when you call this method, it will start getting all the EquationQuestions in the list
     * and counting how many of those isCorrect
     * and stored the score as a global variable (int) called finalScore.
     */
    private void getFinalSCore() {
        for (int i = 0 ; i < 10 ; i++){
            EquationQuestion tempQuestion = equationList.get(i);
            boolean isCorrect = tempQuestion.isCorrect();
            if (isCorrect == true){
                finalScore+=1;
            }
        }
    }

    /**
     * when you call this method, it will start the progress bar
     * it will always be fixed at 3 seconds
     */
    private void startProgressBar(){

        Task<Void> progressBarTask = new Task<Void>() {
            @Override
            public Void call() throws Exception{

                for (double i = 0.0 ; i < 1.0 ; i = i + 0.01){
                    //good luck trying to calculate the exact intervals needed for 3 seconds( or was it 2?)
                    //good luck trying to figure out milliseconds needed....

                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException ie){
                        //do nothing
                    }
                }
                return null;
            }

            @Override
            public void done() {
                //i dunno why, but when the progress bar only goes up to like 95%...and you can see a bit of white
                //not covered by the progress
                //so i have to manually set the progress to 100%....wtf.....
                progressBar.setProgress(1.0);//cheating, not really
            }

        };
        new Thread(progressBarTask).start();
    }

    //===========================================================================================
    //Instruction help popovers

    @FXML
    private void recordHelp(){
        createPopover(recordPopOver, recordButton,"Press this button to record your answer for 2 seconds!");
    }

    @FXML
    private void listenHelp(){
        createPopover(listenPopOver, listenButton,"Press this button to listen to your answer");
    }

    @FXML
    private void confirmHelp(){
        createPopover(confirmPopOver, confirmButton,"Press this button to listen to submit your answer");
    }

    @FXML
    private void attemptHelp(){
        createPopover(attemptPopOver, attemptTracker,"This shows which attempt you're on \nRemember you have 3 tries for each question!");
    }

    @FXML
    private void questionTrackHelp(){
        createPopover(questionTrackerPopOver, questionTracker,"This shows which question you're on!");
    }

    @FXML
    private void retryHelp(){
        createPopover(retryPopOver, retryButton,"Press this button to retry the question!");

    }

    @FXML
    private void skipHelp(){
        createPopover(skipPopOver, skipButton,"Press this button to skip this question");

    }

    @FXML
    private void nextHelp(){
        createPopover(nextPopOver, nextButton,"Pres this button to continue to the next question!");

    }

    @FXML private void recordClose(){ recordPopOver.hide();}
    @FXML private void listenClose(){ listenPopOver.hide();}
    @FXML private void confirmClose(){ confirmPopOver.hide();}
    @FXML private void attemptClose(){ attemptPopOver.hide();}
    @FXML private void questionTrackerClose(){ questionTrackerPopOver.hide();}
    @FXML private void retryClose(){ retryPopOver.hide();}
    @FXML private void skipClose(){ skipPopOver.hide();}
    @FXML private void nextClose(){ nextPopOver.hide();}

    private void createPopover(PopOver popOver, Node anchor, String text){
        Label help = new Label(text);
        help.setFont(new Font("Maiandra GD",20));
        popOver.setContentNode(help);
        popOver.setArrowLocation(TOP_CENTER);
        popOver.setArrowSize(10);
        // remove min height property.
        DoubleProperty minHeight = popOver.getRoot().minHeightProperty();
        minHeight.unbind();
        minHeight.set(0);
        // Set padding
        popOver.getRoot().setPadding(new Insets(8));
        popOver.show(anchor);

    }

}
