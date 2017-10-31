package application.view;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;

import static application.MainApp.mascotImage;
import static org.controlsfx.control.PopOver.ArrowLocation.RIGHT_CENTER;


/**
 * this controller handles to play screen
 * this screen asks the user what options to pick, e.g easy, hard, custom
 */
public class PlaySelectScreenController extends AbstractController {

    @FXML private ImageView mascot;

    //======================================================================================
    // buttons on the screen
    @FXML private Button easyButton;
    @FXML private Button hardButton;
    @FXML private Button customButton;
    @FXML private Button backButton;
    //======================================================================================
    //Pop overs for the buttons
    private PopOver easyPopOver = new PopOver();
    private PopOver hardPopOver = new PopOver();
    private PopOver customPopOver = new PopOver();
    private PopOver backPopOver = new PopOver();

    @FXML
    public void initialize(){
        mascot.setImage(mascotImage);
    }

    @FXML
    public void handleBack(){
        backPopOver.hide();
        _mainApp.initStartMenu();
    }


    @FXML
    public void handleCustom(){
        _mainApp.initCustom();
    }



    //===================================================
    @FXML
    public void handleEasy(){
        easyPopOver.hide();
        _mainApp.initQuestionSelect("easy");
    }


    @FXML
    public void handleHard(){
        hardPopOver.hide();
        _mainApp.initQuestionSelect("hard");
    }
    //==================================================
    //pop over methods
    @FXML
    private void easyHelp(){
        createPopover(easyPopOver, easyButton, "Questions with answers less than 20" );
    }
    @FXML
    private void closeEasyHelp(){
        easyPopOver.hide();
    }
    @FXML
    private void hardHelp(){
        createPopover(hardPopOver, hardButton, "Questions with answers between 1 and 99");
    }
    @FXML
    private void closeHardHelp(){
        hardPopOver.hide();
    }
    @FXML
    private void customHelp(){
        createPopover(customPopOver, customButton, "Create your own question lists!");
    }
    @FXML
    private void closeCustomHelp(){
        customPopOver.hide();
    }
    @FXML
    private void backHelp(){
        createPopover(backPopOver, backButton, "Go back to the Main Menu");
    }
    @FXML
    private void closeBackHelp(){
        backPopOver.hide();
    }


    private void createPopover(PopOver popOver, Node anchor, String text){
        Label help = new Label(text);
        help.setFont(new Font("Maiandra GD",20));
        popOver.setContentNode(help);
        popOver.setArrowLocation(RIGHT_CENTER);
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
