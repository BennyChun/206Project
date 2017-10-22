package application.view;


import application.util.SaveGame;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EasyStatsController extends AbstractController {

    //===================================================================================================
    // overall stats fields
    @FXML private TableView<SaveGame> overallStats;
    @FXML private TableColumn<SaveGame, String> _difficulty;
    @FXML private TableColumn<SaveGame, String> _operation;
    @FXML private TableColumn<SaveGame, String> _score;
    @FXML private TableColumn<SaveGame, String> _time;
    //===================================================================================================
    // hidden table fields
    @FXML private TableView<SaveGame> sessionStats; // hidden table storing equations that were done
    @FXML private TableColumn<SaveGame, Integer> _questionNumber;
    @FXML private TableColumn<SaveGame, String> _equation;
    @FXML private TableColumn<SaveGame, Integer> _answer;
    @FXML private TableColumn<SaveGame, String> _isCorrect;
    @FXML
    private TableColumn<SaveGame, Integer> _attempts;
    //====================================================================================================
    @FXML private Button _openSession;
    @FXML private Button _closeSession;
    @FXML private LineChart<Integer, Integer> _scoreGraph;
    @FXML private Label highScore;




}
