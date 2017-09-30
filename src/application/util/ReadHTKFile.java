package application.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class will read the output file from HTK
 * the file name is called recout.mlf
 * it is located @ 206Poject
 * 
 * @author se206 
 *
 */
public class ReadHTKFile {
	
	private ArrayList<String> maoriWordList = new ArrayList<>();

	public void readHTK() {
		// The name of the file to open. The file will always be call recout.mlf
		String fileName = "recout.mlf";
		
		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			
			while((line = bufferedReader.readLine()) != null) {
				//while the line1 is not null
				//if this line1 is equal to "sil"
				//	get the next line2
				//  while line2 does not equal to "sil
				//		save line2 to final string
				//		get update line2 by getting next line
				//
				//else
				//	then get the next line until it either reaches null or gets the actual word
				
				if (line.equals("sil")) {
					String line2 = bufferedReader.readLine();//gets the next line, stores it as line
					while (!line2.equals("sil") && !line2.equals(".")) {
						maoriWordList.add(line2);
						line2 = bufferedReader.readLine();//gets the next line, stores it as line2

					}
					
				}else {
					//do nothing, go to the next line.
				}
			}   
			
			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ioe) {
			System.out.println("Error reading file '"  + fileName + "'");                  
		}
	}
	
	
	/**
	 * this method is used to test if the words have been saved correclty into the arraylist
	 */
	public void displayString() {
		
		for (int i = 0 ; i < maoriWordList.size() ; i++) {
			System.out.print(maoriWordList.get(i) + " " ) ;
		}
		
	}
	
	/**
	 * this method returns the string of the arraylist of maori words
	 * @return
	 */
	public String getMaoriWords(){
		String maoriString = maoriWordList.get(0);
		
		
		for (int i = 1 ; i < maoriWordList.size() ; i++) {
			maoriString = maoriString + " " + maoriWordList.get(i);
		}	
		
		return maoriString;
				
	}


}
