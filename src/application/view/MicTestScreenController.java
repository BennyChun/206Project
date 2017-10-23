package application.view;

import application.util.ReadHTKFile;
import application.util.RecordingUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

import static application.MainApp.mascotImage;

public class MicTestScreenController extends AbstractController {

    @FXML private Button recordButton;
    @FXML private Button playButton;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView mascot;

    @FXML
    private void initialize(){

        mascot.setImage(mascotImage);
    }

    @FXML
    public void handleBack(){
        _mainApp.initStartMenu();
    }

    @FXML
    public void handleRecord(){
        startProgressBar();


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

    /**
     * when you call this method, it will start the progress bar
     * it will always be fixed at 3 seconds
     */
    private void startProgressBar(){

        Task<Void> progressBarTask = new Task<Void>() {
            @Override
            public Void call() throws Exception{

                for (double i = 0.0 ; i < 1.0 ; i = i + 0.01){
                    //good luck trying to calculate the exact intervals needed for 3 seconds( or was it 2?)
                    //good luck trying to figure out milliseconds needed....

                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException ie){
                        //do nothing
                    }
                }
                return null;
            }

            @Override
            public void done() {
                //i dunno why, but when the progress bar only goes up to like 95%...and you can see a bit of white
                //not covered by the progress
                //so i have to manually set the progress to 100%....wtf.....
                progressBar.setProgress(1.0);//cheating, not really
            }

        };
        new Thread(progressBarTask).start();
    }


}
