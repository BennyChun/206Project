package application.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this class takes a directory for the file to be saved
 * also takes the unixTimeStamp (this is the name of the file to be saved)
 * also takes the jsonString to write to the file.
 *
 */
public class OutPutFile {

    public OutPutFile(String dir ,  long unixTimeStamp ,String jsonString){

        File f = new File(dir + Long.toString(unixTimeStamp));//creates the new File
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(jsonString);//write the json String into the file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
