package application;

import application.util.ReadHTKFile;
import application.util.RecordingUtil;

/**
 * this class is solely used to test the recordingUitl class
 * this doesn't provide any functionality to the main application
 * @author se206
 *
 */
public class testingRecordingUtil {

	
	public static void main(String[] args) {
		RecordingUtil recordingUtil = new RecordingUtil();
		recordingUtil.recordVoice(); // this actually puts the file in 206Project folder
		
		
		recordingUtil.convertVoiceToMaori();// does this work ?
		System.out.println("recording and converting worked !?!?!!");
		
		ReadHTKFile readRecout = new ReadHTKFile();
		readRecout.readHTK();
		
		readRecout.displayString();
	}
	
	
	
	
}
