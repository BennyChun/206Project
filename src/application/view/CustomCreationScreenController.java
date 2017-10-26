package application.view;

import application.model.EquationQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomCreationScreenController extends AbstractController {

    @FXML private TableView customLevelTable;
    @FXML private TableColumn<String, EquationQuestion> equationListNameCol;
    @FXML private TableColumn<String, EquationQuestion> equationCreationDateCol;
    @FXML private Button addBtn;
    @FXML private Button subBtn;
    @FXML private Button mulBtn;
    @FXML private Button divBtn;
    @FXML private TextField boundaryValue;
    @FXML private TextField equationListName;
    @FXML private Button generateButton;

    @FXML
    private void handleBack(){
        _mainApp.initCustom();
    }
    @FXML
    private void generate(){


        _mainApp.initCustom();
    }

    
}
