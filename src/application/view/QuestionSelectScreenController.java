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
    }

    @FXML
    public void handleMinus(){//initMainLevelScreen
        String selectedOperator = "-";
    }

    @FXML
    public void handleMult(){//initMainLevelScreen
        String selectedOperator = "*";
    }

    @FXML
    public void handleDiv(){//initMainLevelScreen
        String selectedOperator = "/";
    }

    /**
     * this method takes an input which represents users selected level
     * @param selectedLevel
     */
    public void setLevel(String selectedLevel) {
    this.selectedLevel=selectedLevel;
    }
}
