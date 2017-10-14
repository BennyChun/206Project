package application.util;

import java.io.IOException;

/**
 * this class will be used to do the recording and playing and
 * @author se206
 *
 */
public class RecordingUtil {

	/**
	 * this method records the voice for 3 seconds
	 * and saves it in the 206Project folder (project folder)
	 * 
	 * this audio wav file will be overridden and is only a temporary file
	 * 
	 */
	public void recordVoice() {
		
		//String command = "ffmpeg -f alsa -i hw:0 -t 2 -acodec pcm_s16le -ar 16000 -ac 1 foo.wav"
		String command = "arecord -d 2 -r 22050 -c 1 -i -t wav -f s16_LE foo.wav";
		
		ProcessBuilder pb = new ProcessBuilder("bash" , "-c" , command );
		try {
			
			Process recordProcess = pb.start();
			
			recordProcess.waitFor();
			
			recordProcess.destroy();
	
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
			
		}catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * this method gets the recorded wav audio file from the 206Porjecy folder
	 * and passes it to HTK.
	 * HTK then outputs a text file that contains what the user said
	 * 
	 * you will need to go into that text file and read what it says
	 */
	public void convertVoiceToMaori() {
		String command = "HVite -H HTK/MaoriNumbers/HMMs/hmm15/macros -H HTK/MaoriNumbers/HMMs/hmm15/hmmdefs -C HTK/MaoriNumbers/user/configLR  -w HTK/MaoriNumbers/user/wordNetworkNum -o SWT -l '*' -i recout.mlf -p 0.0 -s 5.0  HTK/MaoriNumbers/user/dictionaryD HTK/MaoriNumbers/user/tiedList foo.wav";
		
		ProcessBuilder pb = new ProcessBuilder("bash" , "-c" , command );
		try {
			
			Process convertProcess = pb.start();
			
			convertProcess.waitFor();
			
			convertProcess.destroy();
			
	
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
			
		}catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	
	
	
	}

	
	/**
	 * this method plays the wav audio file in the 206Project folder
	 * auto closes after its done
	 */
	public void playRecording() {
		String command = "aplay foo.wav";
		
		ProcessBuilder pb = new ProcessBuilder("bash" , "-c" , command );
		try {
			
			Process playProcess = pb.start();
			
			playProcess.waitFor();
			
			playProcess.destroy();
			
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
			
		}catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	
}
