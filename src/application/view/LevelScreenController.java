package application.view;


import java.io.File;
import java.util.Optional;
import java.util.Random;

import application.model.Question;
import application.util.MaoriAnswerUtil;
import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelScreenController extends AbstractController{
	private String _selectedLevel;
	private Question currentQuestion;  //this is the currency Question object
	private int currentQuestionNumber = 0; //this keeps track of which question  0-9 (can put this in the Question Model maybe ?)
	private String mao="";
	String correctAnswer;
	

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
	public void handleMainMenu() {
		//goes back to the main menu
		_mainApp.initStartMenu();
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


		//+++++++++++++++++++++++++++++ testing the record button ++++++++++++++++++++++++++++++++

		if (currentQuestionNumber <=9 ){ //THIS IS <= 9 BECAUSE THE LIST OF QUESTIONS GOES FROM 0 - 9

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
	 * if CORRECT, the dialog box will have
	 */
	public void processAnswer() {
		MaoriAnswerUtil maoUtil = new MaoriAnswerUtil();
		
		int correctNumber = currentQuestion.getInteger();
		maoUtil.numberToMaori(correctNumber);
		correctAnswer = maoUtil.getMaoriWords();
		System.out.println("user answer: " +mao);
		System.out.println("correct answer: " +correctAnswer);
		
		
		System.out.println(System.getProperty("user.dir"));
		if(mao.equals(correctAnswer)) {
			openCorrectDialog();
		} else if(!(mao.equals(correctAnswer))){
			if (currentQuestion.getAttempts()<2 ) {
				openFirstIncorrectDialog();
			} else if(currentQuestion.getAttempts()==2) {//well obviously it will be 2 if the previous if stqatement is less than 2
				openSecondIncorrectDialog();
			}
				
		} else if(mao.equals("")) {
			
		}

	}
	
	
	//====================================================== DIALOGS ========================================================
	public void openCorrectDialog() {
		Alert correctAlert = new Alert(AlertType.CONFIRMATION);
		correctAlert.setTitle("Answer confirmed");
		correctAlert.setHeaderText("You got the answer Correct");
		correctAlert.setContentText("Correct answer is: " + mao);
		Image image = new Image(new File(System.getProperty("user.dir") + "/Icons/111251-material-design/png/check-symbol.png").toURI().toString(), true);
		ImageView view = new ImageView(image);
		view.setFitWidth(100);
		view.setFitHeight(100);
		correctAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");

		correctAlert.getButtonTypes().setAll(nextButton);

		Optional<ButtonType> result = correctAlert.showAndWait();
		if (result.get() == nextButton){
		    System.out.println("hi");
		} 	 
	}
	
	public void openFirstIncorrectDialog() {
		Alert incorrectAlert = new Alert(AlertType.CONFIRMATION);
		incorrectAlert.setTitle("Answer confirmed");
		incorrectAlert.setHeaderText("You got the answer wrong :(");
		incorrectAlert.setContentText("Please try again, or press next to go to the next question"); // gives the answers 
		
		
		Image image = new Image(new File(System.getProperty("user.dir") + "/Icons/111251-material-design/png/close-button.png").toURI().toString(), true);
		ImageView view = new ImageView(image);
		view.setFitWidth(100);
		view.setFitHeight(100);
		incorrectAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");
		ButtonType retryButton = new ButtonType ("Retry");	
		
		incorrectAlert.getButtonTypes().setAll(retryButton , nextButton);

		Optional<ButtonType> result = incorrectAlert.showAndWait();
		if (result.get() == nextButton){
		    System.out.println("hi");
		}else if (result.get() == retryButton) {
			System.out.println("trying again");
			currentQuestion.addAttempts();	
		}
		
	}
	
	public void openSecondIncorrectDialog() {
		Alert incorrectAlert = new Alert(AlertType.CONFIRMATION);
		incorrectAlert.setTitle("Answer confirmed");
		incorrectAlert.setHeaderText("You got the answer wrong :(");
		incorrectAlert.setContentText("Correct answer is: " + correctAnswer); // gives the answers 
		
		
		Image image = new Image(new File(System.getProperty("user.dir") + "/Icons/111251-material-design/png/clear-button.png").toURI().toString(), true);
		ImageView view = new ImageView(image);
		view.setFitWidth(100);
		view.setFitHeight(100);
		incorrectAlert.graphicProperty().set(view);

		ButtonType nextButton = new ButtonType("Next");
		
		incorrectAlert.getButtonTypes().setAll(nextButton);

		Optional<ButtonType> result = incorrectAlert.showAndWait();
		if (result.get() == nextButton){
		    System.out.println("hi");
		}
		
	}
	
	//================================================================================================================================


	/**
	 * this class will display the current question on the label
	 * and it will be called everytime the user clicks the record button
	 */
	public void processRecord() {


		//		RecordingUtil record = new RecordingUtil();		//instantiates the Recording class so that we can use it's Utilities
		//		record.recordVoice();							//record the users voice
		//		record.convertVoiceToMaori();					//pass the users wav file to the KHT, and HTK will output the foo.mlf file
		//		
		//		ReadHTKFile readRecout = new ReadHTKFile();		//instantiates the ReadHTKFile class
		//		readRecout.readHTK();							//reads the foo.mlf file
		//		String mao = readRecout.getMaoriWords();		//get the String of the maori word (this is the the users input answer)
		//		

		currentQuestion.setCorrect(true);					//make all questions correct >>> testing purposes


	}


	//===========================================================================================================================

	/**
	 * returns a list of questions as an observable list
	 * @return
	 */
	public ObservableList<Question> getQuestionData() {
		return questionData;
	}



	//========================================== Unimplemented methods ==========================================================


	// not needed for the purposes of this assignment, will be implemented in the final project.
	@FXML
	public void handleSkip() {
		//when the user clicks skip, it should go to the next question, 
		//and record the current question to be failed
		//we will not implement the skip button for assignment 3
	}


	@FXML
	public void handleOpenInfo() {
		System.out.println("hello");
	}

	@FXML
	public void handleCloseInfo() {
		System.out.println("bye");
	}
	//=============================== generates the 10 random questions based on user lvl selected ================================
	private void generateNumbers() {
		//this method generates 10 random numbers depending on what the level was
		if (_selectedLevel == "easy") {
			System.out.println("you have selected easy lvl.");

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
	 * @param Question
	 */
	private void showQuestionDetails(Question question) {

		if (question != null) {

			numberLabel.setText(Integer.toString(question.getInteger()));

		} else {

			numberLabel.setText("");
		}
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





}