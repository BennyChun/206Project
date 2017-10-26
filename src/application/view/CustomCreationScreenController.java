package application.view;

import application.model.EquationQuestion;
import application.util.OutputFile;
import application.util.SaveCreationHelper;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Random;

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

        String errorMessage = "";

        ArrayList<String> selectedOperations = getTickBoxes();//this stores what the operations the user has selected    +-+-+
        if (selectedOperations.size() == 0){
            //the user did not select a operation
            errorMessage+= "You did not select an operation, please select one\n";
        }

        //----------------------------------------------------------------------
        String usersBoundary = boundaryValue.getText();//this stores the String for the upper bound  (e.g "80")          +-+-+
        String digitRegex = "\\d+";
        int result;
        if (usersBoundary.length()==0){//if the user did not enter anything for the upper bound
            errorMessage+= "You did not enter a number for the max number\n";
        }
        if (usersBoundary.matches(digitRegex)){
            result = Integer.parseInt(usersBoundary);//converts the users answer to an integer
            //will throw an exception if it cannot parse the string to an integer
            if (result <10 || result >99){
                errorMessage+="Please enter a integer between 10 and 99\n";
            }

        }else {
            //the user did not enter a digit
            errorMessage+="You need to enter a integer !\n";
        }
        //--------------------------------------------

        String usersCreationName = equationListName.getText(); //this stores what the user wants to name their creation.         +-+-+
        if (usersCreationName.length() == 0){
            errorMessage+= "Please enter a name for this custom game \n";
        }



        if (errorMessage.length() == 0) {
            //if the user has no error messages

            //we have the operations in an selectedOperations: ArrayList<String>
            //we have the usersBoundary: String
            //we have the usersCreationName: String

            /////randomly pick the users selected operations
            /////generate the 10 EquationQuestions
            /////somehow generate a JSON string of the 10 equationQuestions, and save the name for it

            Random rand = new Random();
            ObservableList<EquationQuestion> theTenEquations = FXCollections.observableArrayList();

            int numOfOperations = selectedOperations.size();//this gets how many operations the user wants

            System.out.println("-----CUSTOM EQUATIONS CREATED-----"); //testing purpose
            for (int i = 0 ; i < 10 ; i ++){

                int choice = rand.nextInt(((numOfOperations-1) - 0) + 1) + 0;//generate number between 0 and (num of operations the user selected)
                String choiceOperation = selectedOperations.get(choice);

                EquationQuestion tempEquation = new EquationQuestion("custom", choiceOperation , Integer.parseInt(usersBoundary));
                theTenEquations.add(tempEquation);
                //testing purposes

                System.out.print(tempEquation.getTheEquation() + " = ");
                System.out.println(tempEquation.getTheAnswer());

            }
            //---------------------------------------------------------------------------------------
            /////make a EquationQuestionAdapter class for EquationQuestion (has Strings and ints instead of properties)
            /////make a SaveCreationHelper to take in the ObservableList<EquationQuestion> and the creationName
            /////serialize that SaveCreationHelper and save the JSON string
            /////save the JSON string to a txt file , to CustomGames folder

            SaveCreationHelper sch = new SaveCreationHelper(theTenEquations, usersCreationName);

            Gson gson = new Gson();
            String jsonString = gson.toJson(sch);
            System.out.println(jsonString);

            String savedGamesDir = System.getProperty("user.dir")+"/CustomGames/";
            OutputFile out = new OutputFile(savedGamesDir , sch.getUnixTimeStamp(), jsonString);


            //--------------------------------------------------------------------------------------
            //back in the CustomLevelScreenController
            //when the user selects a game to play
            //it should read the JSON string
            //it should somehow get that string
            //and convert everthing back to an ObservableList<EquationQuestion>
            //and then past that ObservableList to the initmainLevelScreen


            //go back to custom screen (the screen that was a TableView of all the users custom games
            //and it lets the user select one to play or create one.
            _mainApp.initCustom();
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return ;
        }



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
