package application.view;


import application.util.InputStatsFile;
import application.util.SaveGame;
import application.util.SaveGameObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class EasyStatsController extends AbstractController {

    //===================================================================================================
    // overall stats fields
    @FXML private TableView<SaveGameObservable> overallStats;
    @FXML private TableColumn<SaveGameObservable, String> _difficulty;
    @FXML private TableColumn<SaveGameObservable, String> _operation;
    @FXML private TableColumn<SaveGameObservable, Integer> _score;
    @FXML private TableColumn<SaveGameObservable, String> _time;
    //===================================================================================================
    // hidden table fields
    @FXML private TableView<SaveGameObservable> sessionStats; // hidden table storing equations that were done
    @FXML private TableColumn<SaveGameObservable, Integer> _questionNumber;
    @FXML private TableColumn<SaveGameObservable, String> _equation;
    @FXML private TableColumn<SaveGameObservable, Integer> _answer;
    @FXML private TableColumn<SaveGameObservable, String> _isCorrect;
    @FXML
    private TableColumn<SaveGameObservable, Integer> _attempts;
    //====================================================================================================
    @FXML private Button _openSession;
    @FXML private Button _closeSession;
    @FXML private LineChart<Integer, Integer> _scoreGraph;
    @FXML private Label highScore;
    //==========================================================================================
    //                                   global fields

    private String userSelectedStatsLevel; //either (easy , hard , custom)
    private ObservableList<SaveGameObservable> observableArrayList = FXCollections.observableArrayList();
    //==============================================================================================
    //                                 INITIAL SET UPS
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EasyStatsController() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        // firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        // lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        _difficulty.setCellValueFactory(cellData -> cellData.getValue().theLevelProperty());
        _operation.setCellValueFactory(cellData -> cellData.getValue().theOperationProperty());
        _score.setCellValueFactory(cellData -> cellData.getValue().theScoreProperty().asObject());
        _time.setCellValueFactory(cellData -> cellData.getValue().theDateProperty());




    }

    /**
     * thos method takes a String which is the users selected button (easy , hard, custom)
     * @param selectedLevelButton
     */
    public void initialSetUp(String selectedLevelButton){

        userSelectedStatsLevel = selectedLevelButton; //saves what the user selected
        //should get al lth stats
        InputStatsFile stats = new InputStatsFile();
        stats.getFiles();

        observableArrayList = stats.getObservableList(selectedLevelButton);
        overallStats.setItems(observableArrayList);

        setUpHighScoreLabel();
        setUpLineChart();
    }


    //=============================================================================================
    //                                        helper methods
    //=============================================================================================

    /**
     * when you call this method, it will go through all the SaveGameObservable objects
     * and gets the highest score.
     */
    private void setUpHighScoreLabel() {
        int size = observableArrayList.size();

        if (size > 0) {
            int highestScore = observableArrayList.get(0).getTheScore();//gets the score of the first savegame, make it the highest

            for (int i = 1; i < size; i++) {
                SaveGameObservable temp = observableArrayList.get(i);
                int tempScore = temp.getTheScore();
                if (tempScore >= highestScore) {
                    highestScore = tempScore;
                }
            }

            highScore.setText(String.valueOf(highestScore));
        }else{
            highScore.setText(String.valueOf(0));
        }
    }

    /**
     * this method will set up the line chart to show the progression of the last (10 games ? )
     */
    private void setUpLineChart(){
        ArrayList<Integer> history = new ArrayList<>();
        //for (int i = 0; )

    }
}
