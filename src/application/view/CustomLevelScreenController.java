package application.view;

import application.model.EquationQuestion;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;

import static application.MainApp.mascotImage;
import static org.controlsfx.control.PopOver.ArrowLocation.TOP_CENTER;

public class CustomLevelScreenController extends AbstractController{

    @FXML private ImageView mascot;
    @FXML private TableView customLevelTable;
    @FXML private TableColumn<String, EquationQuestion> equationListNameCol;
    @FXML private TableColumn<String, EquationQuestion> equationCreationDateCol;
    @FXML private Button playBtn;
    @FXML private Button addBtn;
    @FXML private Button delBtn;
    private PopOver playLevelPopOver = new PopOver();
    private PopOver addLevelPopOver = new PopOver();
    private PopOver deleteLevelPopOver = new PopOver();

    @FXML
    private void initialize(){

        mascot.setImage(mascotImage);
    }


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
        //check if the user clicked on a equationlist
        //if they have, pass that equation list to the LevelScreenController, and load the FXML for that
        hidePopOvers();
    }

    @FXML
    public void handleDelete(){
        //check if the user selected an item on the view
        //if they have, as if they want to delete, if they say yes
        //go to the CustomGames folder and delete it
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
