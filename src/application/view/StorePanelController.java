package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;

import java.net.URISyntaxException;

public class StorePanelController extends AbstractController {

    @FXML private RadioButton kiwiButton;
    @FXML private RadioButton keaButton;
    @FXML private RadioButton kakapoButton;

    @FXML
    public void initialize(){
        // read file to check selected image from before
    }

    @FXML public void setKiwi(){
        String path = null;
        try {
            path = this.getClass().getResource("kiwi.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(path);
        kiwiButton.setSelected(true);
    }
    @FXML public void setKea(){
        String path = null;
        try {
            path = this.getClass().getResource("Kea.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(path);
        keaButton.setSelected(true);
    }
    @FXML public void setKakapo(){
        String path = null;
        try {
            path = this.getClass().getResource("kakapo.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(path);
        kakapoButton.setSelected(true);
    }


}
