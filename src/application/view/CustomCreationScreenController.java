package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

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

        //when the user clicks the generate button, it should get what the user has selected from the 4 tick boxes
        //need to check if any of the tick boxes are selected
        //then check if the user entered a number (boundaryValue) if between 10 and 99 (if we allow them to set the upper bound to be like 1, that'll be stupid)
        //then get the equationListName , set as a string

        ArrayList<String> selectedOperations = getTickBoxes();


        _mainApp.initCustom();
    }

    /**
     * this method will get the selected tick boxes
     * it will add those to an ArrayList<String>
     * it will then return that arraylist
     *
     * the caller of thise method will need to check if the size of the returned array is > 0
     * if the returned array is not >0 , then the user did not selected a tick box
     * </String>
     */
    private ArrayList<String> getTickBoxes() {
        ArrayList<String> temp = new ArrayList<>();

        if (addCheck.isSelected()){
            temp.add("+");
        }
        if (subCheck.isSelected()){
            temp.add("-");
        }
        if (mulCheck.isSelected()){
            temp.add("*");
        }
        if (divCheck.isSelected()){
            temp.add("/");
        }
        return temp;
    }


    //=================================================================
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
