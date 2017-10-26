package application.view;

import application.model.EquationQuestion;
import application.util.InputCreationFile;
import application.util.SaveCreationHelper;
import application.util.SaveGameObservable;
import application.util.SavedCreationObservable;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.util.Optional;

import static application.MainApp.mascotImage;
import static org.controlsfx.control.PopOver.ArrowLocation.TOP_CENTER;

public class CustomLevelScreenController extends AbstractController{

    @FXML private ImageView mascot;

    //=================================================================================================
    @FXML private TableView<SavedCreationObservable> customLevelTable; //this Table view stuff

    @FXML private TableColumn<SavedCreationObservable, String> equationListNameCol;      //column for the name
    @FXML private TableColumn<SavedCreationObservable, String> equationCreationDateCol;  //column for the date
    //=================================================================================================

    @FXML private Button playBtn;
    @FXML private Button addBtn;
    @FXML private Button delBtn;
    //=================================================================================================
    private PopOver playLevelPopOver = new PopOver();
    private PopOver addLevelPopOver = new PopOver();
    private PopOver deleteLevelPopOver = new PopOver();
    //=================================================================================================
    //                            global fields


    private ObservableList<SavedCreationObservable> observableArrayList = FXCollections.observableArrayList();
    //=================================================================================================


    @FXML
    private void initialize(){

        mascot.setImage(mascotImage);

        //_difficulty.setCellValueFactory(cellData -> cellData.getValue().theLevelProperty());
        equationListNameCol.setCellValueFactory(cellData -> cellData.getValue().creationNameProperty());
        equationCreationDateCol.setCellValueFactory(cellData -> cellData.getValue().theDateProperty());

    }

    public void initialSetUp(){
        //this should go into CustomGames folder
        //and load all its files
        //convert each JSON from each file to a SaveCreationHelper
        //the SaveCreationHelper contains the primitive types for EquationQuestions (EquationQuestionAdapter)
        //you need to convert the SaveCreationHelper to a SaveCreationObservable

        InputCreationFile creations = new InputCreationFile();
        creations.getFiles();
        observableArrayList = creations.getObervableSavedCreations();//this will get the observable data of SavedCreationObservable
        customLevelTable.setItems(observableArrayList);


        playBtn.setDisable(true);//disable the play button
        delBtn.setDisable(true);//disable the delete button
        customLevelTable.getSelectionModel().selectedItemProperty().addListener(event -> playBtn.setDisable(false));
        customLevelTable.getSelectionModel().selectedItemProperty().addListener(event -> delBtn.setDisable(false));

    }


    //===============================================================================================================
    //this controller should also try and populate the ListView with all custom made equations

    @FXML
    public void handleBack(){
        hidePopOvers();
        _mainApp.initPlaySelect();
    }

    @FXML
    public void handleAdd(){

        //should open up a new scene, where the user cna make their equation, and submit
        hidePopOvers();
        _mainApp.initCreateCustom();

    }

    @FXML
    public void handlePlay(){
        hidePopOvers();

        //when the user selects a game to play
        //it should read the JSON string
        //it should somehow get that string
        //and convert everthing back to an ObservableList<EquationQuestion>
        //and then past that ObservableList to the initmainLevelScreen

        if (customLevelTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        SavedCreationObservable game = customLevelTable.getSelectionModel().getSelectedItem();
        ObservableList<EquationQuestion> equationQuestionList = game.getCustomEquations(); // gets the equationQuestions
        _mainApp.initMainLevelScreen(game.getCreationName(), "custom" , equationQuestionList );

    }

    @FXML
    public void handleDelete(){
        int selectedIndex = customLevelTable.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to Delete this saved game ?");
        alert.setContentText("Once deleted, you can never get it back :(");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){


            //also delete it from the SavedGamesStats folder
            String savedGamesDir = System.getProperty("user.dir")+"/CustomGames/";

            SavedCreationObservable temp = customLevelTable.getItems().get(selectedIndex);//this gets the SaveGame that was selected from the user on the Tableview
            String filename = Long.toString(temp.getUnixTimeStamp());
            File fileToDelete = new File(savedGamesDir + filename);
            fileToDelete.delete();

            customLevelTable.getItems().remove(selectedIndex);//delete the game form the TableView

            //this checks if the list is empty
            //if it is ,disable the play button and delete button
            if(observableArrayList.size() ==0){
                playBtn.setDisable(true);
                delBtn.setDisable(true);
            }
        } else {
            //do nothing
        }


    }

    /**
     * show helper for what the play button does
     */
    @FXML private void showPlay(){
        createPopover(playLevelPopOver, playBtn, "Select your level and press this to play!");
    }

    /**
     * show helper for what the add button does
     */
    @FXML private void showAdd(){
        createPopover(addLevelPopOver, addBtn, "Click to create a new level!");
    }

    /**
     * show helper for what the delete button does
     */
    @FXML private void showDel(){
        createPopover(deleteLevelPopOver, delBtn, "Click to delete one of your levels");
    }

    //these 3 methods hide the popovers once mouse exits the button areas.
    @FXML private void hidePlay(){playLevelPopOver.hide();}
    @FXML private void hideAdd(){addLevelPopOver.hide();}
    @FXML private void hideDel(){deleteLevelPopOver.hide();}

    /**
     * Hides all popovers to stop null pointer exceptions from occuring.
     */
    private void hidePopOvers(){
        playLevelPopOver.hide();
        addLevelPopOver.hide();
        deleteLevelPopOver.hide();
    }
    private void createPopover(PopOver popOver, Node anchor, String text){
        Label help = new Label(text);
        help.setFont(new Font("Maiandra GD",20));
        popOver.setContentNode(help);
        popOver.setArrowLocation(TOP_CENTER);
        popOver.setArrowSize(10);
        // remove min height property.
        DoubleProperty minHeight = popOver.getRoot().minHeightProperty();
        minHeight.unbind();
        minHeight.set(0);
        // Set padding
        popOver.getRoot().setPadding(new Insets(8));
        popOver.show(anchor);

    }



}
