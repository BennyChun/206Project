package application.view;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;

import application.MainApp;
import application.model.Question;
import application.util.MaoriAnswerUtil;
import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.applet.Main;

public class PracticeLevelScreenController extends AbstractController{
	private String _selectedLevel;
	private Question currentQuestion; //this is the current Question object
	private int currentQuestionNumber = 1; //this keeps track of which question  0-9 (can put this in the Question Model maybe ?)
	private String mao="";
	private String correctAnswer;
	private int finalScore;
	private String currentLevel; // stores the current level

	@FXML private ProgressBar progressBar;//the progress bar

	@FXML 
	private Label currentQuestionNumberLabel;

	@FXML
	private Label currentAttempts;

	@FXML
	private Label numberLabel;

	@FXML
	private Button confirmButton;

	@FXML
	private Button playButton;

	@FXML
	private Button recordButton;

	//this will store the 10 question objects, using obserablelist helps us keep track of updates to the question object
	private ObservableList<Question> questionData = FXCollections.observableArrayList();


	@FXML
	public void handleBack() {
		//goes back to the main menu
		_mainApp.initInstructions();
	}


	//=================================================================================================================================
	/***
	 * this method takes a string which should be either easy or hard
	 * and it will generate 10 random numbers 
	 * these 10 random numbers will depend on what was passed into this method
	 * those 10 random numbers will be stored in an: ObservableList<Question> questionData
	 * @param selectedLevel
	 */
	public void setLevel(String selectedLevel) {
		_selectedLevel=selectedLevel;
		generateNumbers();//calls the generateNumbers() method, which generates 10 random numbers depending on which level the user selected

		//display the 10 questions on console
		display();//this method only displays to the console, this is for testing purposes

		//sets the current question
		currentQuestion = questionData.get(0); 
		showQuestionDetails(currentQuestion);// shows the current question
		showCurrentAttempts();
		showCurrentQuestionNumber();

	}

	@FXML
	public void handleConfirm() {
		processAnswer();

	}

	@FXML
	public void handleListen() {
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



	//========================================= handles the recording ==========================================================

	@FXML
	public void handleRecord() {

		startProgressBar();
		//+++++++++++++++++++++++++++++ testing the record button ++++++++++++++++++++++++++++++++

		if (currentQuestionNumber <=11 ){ //THIS IS <= 9 BECAUSE THE LIST OF QUESTIONS GOES FROM 0 - 9

			Task<Void> recordTask = new Task<Void>() {
				@Override
				public Void call() {
					playButton.setDisable(true);
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
						playButton.setDisable(false);
						confirmButton.setDisable(false);
					});
				}
			};
			new Thread(recordTask).start();



		}else {// all 10 questions has been answered
			System.out.println("you've finished the " + currentQuestionNumber + " questions!");//used for testing purposes
			getResults();
		}
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	}






	/**
	 * this method opens up a dialog box which  hshows the user if they got the question CORRECT or WRONG
	 *
	 * THIS METHOD GETS INSTANTLY CALLED WHEN HT EUSER CLICKS COMFIRM
	 * if CORRECT, the dialog box will have
	 */
	public void processAnswer() {
		MaoriAnswerUtil maoUtil = new MaoriAnswerUtil();

		int correctNumber = currentQuestion.getInteger();
		maoUtil.numberToMaori(correctNumber);
		correctAnswer = maoUtil.getMaoriWords();
		System.out.println("user answer: " +mao);
		System.out.println("correct answer: " +correctAnswer);

		if(mao.equals(correctAnswer)) {
			openCorrectDialog();
		} else if(!(mao.equals(correctAnswer))){
			if (currentQuestion.getAttempts()<2 ) {
				openFirstIncorrectDialog();
			} else if(currentQuestion.getAttempts()==2) {//well obviously it will be 2 if the previous if stqatement is less than 2
				openSecondIncorrectDialog();
			}

		} else if(mao.equals("")) {
			//there was no input from the user
		}

	}


	//====================================================== DIALOGS ========================================================
	public void openCorrectDialog() {
		Alert correctAlert = new Alert(AlertType.CONFIRMATION);
		correctAlert.setTitle("Correct");
		correctAlert.setHeaderText("You got the answer Correct ! ");
		correctAlert.setContentText("Correct answer is: " + mao);
		String path = null;
		try {
			path = this.getClass().getResource("check-symbol.png").toURI().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView view = new ImageView(path);
		view.setFitWidth(100);
		view.setFitHeight(100);
		correctAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");

		correctAlert.getButtonTypes().setAll(nextButton);

		Optional<ButtonType> result = correctAlert.showAndWait();
		if (result.get() == nextButton){
			if(currentQuestionNumber == 10) {

				currentQuestion.setCorrect(true);

				System.out.println("you've finished the " + currentQuestionNumber + " questions!");//used for testing purposes
				getResults();

				_mainApp.initEndScreen(finalScore, currentLevel);
			} else {
				System.out.println("Next Question");
				/*
				 * should change the State "correct" for this Question to be TRUE ( they got this question right)
				 * and it should update the currentQuestion to the next one
				 * display it on the Label
				 * and update the currentQuestionNumber
				 * disable the playButton and confirmButton
				 */
				currentQuestion.setCorrect(true);
				currentQuestion = questionData.get(currentQuestionNumber);
				showQuestionDetails(currentQuestion);
				currentQuestionNumber++;
				playButton.setDisable(true);
				confirmButton.setDisable(true);
				showCurrentQuestionNumber();
				showCurrentAttempts();
			}
		} 	 
	}

	public void openFirstIncorrectDialog() {
		Alert incorrectAlert = new Alert(AlertType.CONFIRMATION);
		incorrectAlert.setTitle("Incorrect");
		incorrectAlert.setHeaderText("Incorrect, you said: "+ mao);
		incorrectAlert.setContentText("Please try again, or go to the next question."); 


		String path = null;
		try {
			path = this.getClass().getResource("clear-button.png").toURI().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView view = new ImageView(path);
		view.setFitWidth(100);
		view.setFitHeight(100);
		incorrectAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");
		ButtonType retryButton = new ButtonType ("Retry");	

		incorrectAlert.getButtonTypes().setAll(retryButton , nextButton);

		Optional<ButtonType> result = incorrectAlert.showAndWait();

		if(currentQuestionNumber == 10) {
			if (result.get() == nextButton) {
				currentQuestion.setCorrect(false);
				
				System.out.println("you've finished the " + currentQuestionNumber + " questions!");//used for testing purposes
				getResults();
				
				_mainApp.initEndScreen(finalScore, currentLevel);
			}else {
				currentQuestion.addAttempts();	 // add +1 to attempts for this Question

				showCurrentAttempts();

				//disable playButton and confirmButton
				playButton.setDisable(true);
				confirmButton.setDisable(true);
			}

		}else if (result.get() == nextButton){
			System.out.println("Next Question");

			/*
			 * it should change the state "correct" for this Question to be FALSE ( they got this question wrong)
			 * and it should update the currentQuestion to the next one
			 * display it on the label
			 * and update the currentQuestionNumber
			 * disable playButton and confirmButton
			 */

			currentQuestion.setCorrect(false);
			currentQuestion = questionData.get(currentQuestionNumber);
			showQuestionDetails(currentQuestion);
			currentQuestionNumber++;
			playButton.setDisable(true);
			confirmButton.setDisable(true);
			showCurrentQuestionNumber();
			showCurrentAttempts();

		}else if (result.get() == retryButton) {
			System.out.println("trying again");
			currentQuestion.addAttempts();	 // add +1 to attempts for this Question

			showCurrentAttempts();

			//disable playButton and confirmButton
			playButton.setDisable(true);
			confirmButton.setDisable(true);
		}

	}

	public void openSecondIncorrectDialog() {
		Alert incorrectAlert = new Alert(AlertType.CONFIRMATION);
		incorrectAlert.setTitle("Incorrect");
		incorrectAlert.setHeaderText("Incorrect, you said: " + mao);
		incorrectAlert.setContentText("The correct answer was: " + correctAnswer); // gives the answers 
		
		String path = null;
		try {
			path = this.getClass().getResource("clear-button.png").toURI().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView view = new ImageView(path);
		view.setFitWidth(100);
		view.setFitHeight(100);
		incorrectAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");

		incorrectAlert.getButtonTypes().setAll(nextButton);

		Optional<ButtonType> result = incorrectAlert.showAndWait();

		if(currentQuestionNumber == 10) {


			System.out.println("you've finished the " + currentQuestionNumber + " questions!");//used for testing purposes
			getResults();

			_mainApp.initEndScreen(finalScore, currentLevel);//pass finalScore and current level

		}else if (result.get() == nextButton){
			System.out.println("Next Question");

			/*
			 * it should change the state "correct" for this Question to be FALSE ( they got this question wrong)
			 * and it should update the currentQuestion to the next one
			 * display it on the label
			 * and update the currentQuestionNumber
			 * disable playButton and confirmButton
			 */

			currentQuestion.setCorrect(false);
			currentQuestion = questionData.get(currentQuestionNumber);
			showQuestionDetails(currentQuestion);
			currentQuestionNumber++;

			playButton.setDisable(true);
			confirmButton.setDisable(true);
			showCurrentQuestionNumber();
			showCurrentAttempts();
		}

	}


	//===========================================================================================================================

	/**
	 * returns a list of questions as an observable list
	 * @return
	 */
	public ObservableList<Question> getQuestionData() {
		return questionData;
	}

	//=============================== generates the 10 random questions based on user lvl selected ================================

	/**
	 * this is called right from the start of this level
	 * is generates the 10 questions right from the start
	 * and will use this List to store the 10 questions
	 */
	private void generateNumbers() {
		//this method generates 10 random numbers depending on what the level was
		if (_selectedLevel == "easy") {
			System.out.println("you have selected easy lvl.");

			currentLevel = "easy";
			//generate random numbers between 1 and 99
			for (int i = 1 ; i <= 10; i++) {
				Random rand = new Random();

				//nextInt is normally exclusive of the top value,
				//so add one to make it inclusive
				int randomNum = rand.nextInt((9 - 1)+1) +1;

				//make the question object with this random number
				Question theQuestion = new Question(randomNum);

				//add the question object into the observable list
				questionData.add(theQuestion);
			}

		}else {
			System.out.println("you have selected: hard lvl.");

			currentLevel = "hard";
			//generate 10 numbers between 1 and 99
			for (int i = 1 ; i <= 10; i++) {
				Random rand = new Random();

				//nextInt is normally exclusive of the top value,
				//so add one to make it inclusive
				int randomNum = rand.nextInt((99 - 1)+1) +1;

				//make the question object with this random number
				Question theQuestion = new Question(randomNum);

				//add the question object into the observable list
				questionData.add(theQuestion);
			}
		}
	}

	//============================================== updates the numberLabel ========================================================

	/**
	 * this fills the Label for which the number is to be answered
	 * from the Question object
	 * 
	 * 
	 * it takes a Question object, and displays the integer that is associated with that question
	 */
	private void showQuestionDetails(Question question) {

		if (question != null) {

			numberLabel.setText(Integer.toString(question.getInteger()));

		} else {

			numberLabel.setText("");
		}
	}


	/**
	 * this methed when called updates the current attemp
	 */
	private void showCurrentAttempts() {

		currentAttempts.setText (Integer.toString(currentQuestion.getAttempts()) + "/2");

	}

	private void showCurrentQuestionNumber() {
		currentQuestionNumberLabel.setText("Question number: " + Integer.toString(currentQuestionNumber ));
	}



	//=============================================== testing stuff ====================================================

	/**
	 * this method is only used for testing
	 * this method gets the results from all 10 questions
	 */
	public void getResults() {
		boolean tempCorrect;
		int score = 0;
		for (Question q : questionData) {
			tempCorrect = q.isCorrect(); // gets true/false if the question was answered correctly

			if (tempCorrect == true) {//if the question got answered correctly
				score+=1;//Increment score
			}
		}
		System.out.println("you got: " + score + " out of 10 Questions correct");

		finalScore = score;

	}

	/**
	 * this method is solely used for testing: 
	 * this method prints out the current (10) questions that are stored in the observable list to the console
	 */

	private void display() {
		for (Question q : questionData ) {
			System.out.println(q.getInteger());
		}
	}

	//===================================================================================================
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



}