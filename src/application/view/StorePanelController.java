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
        System.out.println(_mainApp.imagePath);
        if(_mainApp.imagePath.equals("file:/C:/Users/BennyChun-PC/eclipse-workspace/206Project/bin/application/view/kiwi.png")){
            kiwiButton.setSelected(true);
        } else if(_mainApp.imagePath.equals("file:/C:/Users/BennyChun-PC/eclipse-workspace/206Project/bin/application/view/Kea.png")){
            keaButton.setSelected(true);
        } else if(_mainApp.imagePath.equals("file:/C:/Users/BennyChun-PC/eclipse-workspace/206Project/bin/application/view/kakapo.png")){
            kakapoButton.setSelected(true);
        }

    }

    @FXML public void setKiwi(){
        try {
            _mainApp.imagePath = this.getClass().getResource("kiwi.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(_mainApp.imagePath);

    }
    @FXML public void setKea(){
        try {
            _mainApp.imagePath = this.getClass().getResource("Kea.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(_mainApp.imagePath);

    }
    @FXML public void setKakapo(){
        try {
            _mainApp.imagePath = this.getClass().getResource("kakapo.png").toURI().toString();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _mainApp.mascotImage = new Image(_mainApp.imagePath);

    }


}
