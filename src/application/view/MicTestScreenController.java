package application.view;

import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MicTestScreenController extends AbstractController {

    @FXML private Button recordButton;
    @FXML private Button playButton;

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void handleRecord(){
        Task<Void> recordTask = new Task<Void>() {
            @Override
            public Void call() {
                playButton.setDisable(true);
                RecordingUtil record = new RecordingUtil();		//instantiates the Recording class so that we can use it's Utilities
                record.recordVoice();							//record the users voice
                record.convertVoiceToMaori();					//pass the users wav file to the KHT, and HTK will output the foo.mlf file
                ReadHTKFile readRecout = new ReadHTKFile();		//instantiates the ReadHTKFile class
                readRecout.readHTK();							//reads the foo.mlf file
                return null;
            }
            @Override
            public void done() {
                Platform.runLater(() -> {
                    playButton.setDisable(false);
                });
            }
        };
        new Thread(recordTask).start();

    }

    @FXML
    public void handlePlay(){
        Task<Void> playRecordingTask = new Task<Void>() {
            @Override
            public Void call() {
                recordButton.setDisable(true);
                RecordingUtil play = new RecordingUtil();
                play.playRecording();
                return null;
            }
            @Override
            public void done() {
                Platform.runLater(() ->{
                    recordButton.setDisable(false);
                });
            }
        };
        new Thread(playRecordingTask).start();

    }


}
