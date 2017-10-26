package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomCreationScreenController extends AbstractController {


    @FXML private Button addBtn;
    @FXML private Button subBtn;
    @FXML private Button mulBtn;
    @FXML private Button divBtn;
    @FXML private TextField boundaryValue;
    @FXML private TextField equationListName;
    @FXML private Button generateButton;
    @FXML private CheckBox addCheck;
    @FXML private CheckBox subCheck;
    @FXML private CheckBox mulCheck;
    @FXML private CheckBox divCheck;

    @FXML
    private void handleBack(){
        _mainApp.initCustom();
    }
    @FXML
    private void generate(){


        _mainApp.initCustom();
    }

    @FXML
    private void handleAdd(){
        if(addCheck.isSelected()){
            addCheck.setSelected(false);
        } else {
            addCheck.setSelected(true);
        }
    }
    @FXML
    private void handleSub(){
        if(subCheck.isSelected()){
            subCheck.setSelected(false);
        } else {
            subCheck.setSelected(true);
        }
    }

    @FXML
    private void handleMul(){
        if(mulCheck.isSelected()) {
            mulCheck.setSelected(false);
        } else{
            mulCheck.setSelected(true);
        }
    }
    @FXML
    private void handleDiv(){
        if(divCheck.isSelected()){
            divCheck.setSelected(false);
        } else {
            divCheck.setSelected(true);
        }
    }




}
