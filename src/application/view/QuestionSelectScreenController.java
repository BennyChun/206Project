package application.view;

import javafx.fxml.FXML;

public class QuestionSelectScreenController extends AbstractController{

    String selectedLevel;

    @FXML
    public void handleBack(){
        _mainApp.initPlaySelect();
    }


    @FXML
    public void handleAdd(){//initMainLevelScreen

        String selectedOperator = "+";
        _mainApp.initMainLevelScreen(selectedLevel , selectedOperator, null);
    }

    @FXML
    public void handleMinus(){
        String selectedOperator = "-";
        _mainApp.initMainLevelScreen(selectedLevel , selectedOperator, null);
    }

    @FXML
    public void handleMult(){//initMainLevelScreen

        String selectedOperator = "*";
        _mainApp.initMainLevelScreen(selectedLevel , selectedOperator, null);
    }

    @FXML
    public void handleDiv(){//initMainLevelScreen

        String selectedOperator = "/";
        _mainApp.initMainLevelScreen(selectedLevel , selectedOperator, null);
    }

    /**
     * this method takes an input which represents users selected level
     * @param selectedLevel
     */
    public void setLevel(String selectedLevel) {
    this.selectedLevel=selectedLevel;
    }
}
