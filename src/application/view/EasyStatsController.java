package application.view;


import application.util.InputStatsFile;
import application.util.SaveGame;
import application.util.SaveGameObservable;
import application.util.SessionObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class EasyStatsController extends AbstractController {

    //===================================================================================================
    // overall stats fields
    @FXML private TableView<SaveGameObservable> overallStats;
    @FXML private TableColumn<SaveGameObservable, String> _difficulty;
    @FXML private TableColumn<SaveGameObservable, String> _operation;
    @FXML private TableColumn<SaveGameObservable, Integer> _score;
    @FXML private TableColumn<SaveGameObservable, String> _time;
    @FXML private TableColumn<SaveGameObservable, Integer> _points;
    //===================================================================================================
    // hidden table fields
    @FXML private TableView<SessionObservable> sessionStats; // hidden table storing equations that were done
    @FXML private TableColumn<SessionObservable, String> _equation;
    @FXML private TableColumn<SessionObservable, Integer> _answer;
    @FXML private TableColumn<SessionObservable, String> _isCorrect;
    @FXML private TableColumn<SessionObservable, Integer> _attempts;
    //====================================================================================================
    @FXML private Button _openSession;
    @FXML private Button _closeSession;
    @FXML private Button delete;
    @FXML private LineChart<Integer, Integer> _OLDscoreGraph;


    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private LineChart<?,?> _scoreGraph ;


    @FXML private Label highScore;
    @FXML private Label StatLevelLabel;
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
        _points.setCellValueFactory(cellData -> cellData.getValue().thePointsProperty().asObject());
        //how to set the cellData to show an arralist of Strings


    }

    /**
     * thos method takes a String which is the users selected button (easy , hard, custom)
     * @param selectedLevelButton
     */
    public void initialSetUp(String selectedLevelButton){

        userSelectedStatsLevel = selectedLevelButton; //saves what the user selected
        StatLevelLabel.setText(selectedLevelButton.substring(0,1).toUpperCase() + selectedLevelButton.substring(1) + " Statistics");
        //should get all the stats
        InputStatsFile stats = new InputStatsFile();
        stats.getFiles();

        observableArrayList = stats.getObservableList(selectedLevelButton);

        overallStats.setItems(observableArrayList);

        _openSession.setDisable(true);
        delete.setDisable(true);

        overallStats.getSelectionModel().selectedItemProperty().addListener(event -> _openSession.setDisable(false));
        overallStats.getSelectionModel().selectedItemProperty().addListener(event -> delete.setDisable(false));

        setUpHighScoreLabel();
        setUpLineChart();
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteStats() {
        int selectedIndex = overallStats.getSelectionModel().getSelectedIndex();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to Delete this saved game ?");
        alert.setContentText("All statistics of this game will be lost");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){



            //also delete it from the SavedGamesStats folder
            String savedGamesDir = System.getProperty("user.dir")+"/SavedGamesStats/";

            SaveGameObservable temp = overallStats.getItems().get(selectedIndex);//this gets the SaveGame that was selected from the user on the Tableview
            String filename = Long.toString(temp.getUnixTimeStamp());
            File fileToDelete = new File(savedGamesDir + filename);
            fileToDelete.delete();

            overallStats.getItems().remove(selectedIndex);//delete the game form the TableView

            //this checks if the list is empty
            //if it is ,disable the openSession button and delete button
            if(observableArrayList.size() ==0){
                _openSession.setDisable(true);
                delete.setDisable(true);
            }


        } else {
            //do nothing
        }


    }


    public void handleOpenSession(){

        if (overallStats.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        SaveGameObservable game = overallStats.getSelectionModel().getSelectedItem();

        //disable the opensession
        //disable the delete button
        //enable the close session button
        //hide showoverall table
        //show sessionStats

        _openSession.setVisible(false);
        _openSession.setDisable(true);
        delete.setDisable(true);
        delete.setVisible(false);
        _closeSession.setVisible(true);
        _closeSession.setDisable(false);
        overallStats.setVisible(false);
        overallStats.setDisable(true);
        sessionStats.setDisable(false);

        _equation.setCellValueFactory(cell -> cell.getValue().equationProperty());
        _answer.setCellValueFactory(cell -> cell.getValue().answerProperty().asObject());
        _isCorrect.setCellValueFactory(cell -> cell.getValue().correctProperty());
        _attempts.setCellValueFactory(cell -> cell.getValue().numberOfAttemptsProperty().asObject());

        sessionStats.setItems(game.getSessionObservables());
        sessionStats.setVisible(true);
    }

    public void handleCloseSession(){
        //disable close session button
        //enable delete button
        //enable open session button
        //show overall table
        //hide sessionStats

        _openSession.setVisible(true);
        _openSession.setDisable(false);
        delete.setDisable(false);
        delete.setVisible(true);
        _closeSession.setVisible(false);
        _closeSession.setDisable(true);
        overallStats.setVisible(true);
        overallStats.setDisable(false);
        sessionStats.setDisable(true);
        sessionStats.setVisible(false);

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
            int highestScore = observableArrayList.get(0).getThePoints();//gets the score of the first savegame, make it the highest


            //find max algorithm
            for (int i = 1; i < size; i++) {
                SaveGameObservable temp = observableArrayList.get(i);
                int tempScore = temp.getThePoints();
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
        int theLength ;
        if (observableArrayList.size() >10){
            //if there are more than 10 questions
            //only make it show the 10 questions
            theLength = 10;
        }else{
            theLength = observableArrayList.size();
        }

        //Sort observableArrayList
        Collections.sort(observableArrayList);

        for (int i = 0; i < theLength ; i ++){
            history.add(observableArrayList.get(i).getThePoints());
        }
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Progression");
        //populating the series with data
        for (int i = 0 ; i < history.size() ; i ++){
            String string = Integer.toString(i);
            series.getData().add(new XYChart.Data(string , history.get(i)));
        }

        _scoreGraph.getData().add(series);
    }



}
