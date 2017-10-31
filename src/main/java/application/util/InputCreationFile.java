package application.util;


import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;


/**
 * this class should read all the txt files in the CustomGames folder
 */
public class InputCreationFile {

    private String customGamesDir = System.getProperty("user.dir")+"/CustomGames/";
    private Gson gson = new Gson();
    private ArrayList<SaveCreationHelper> listOfCreations = new ArrayList<>();
    private ObservableList<SavedCreationObservable> observableArrayList = FXCollections.observableArrayList();


    public void getFiles(){
        File folder = new File(customGamesDir);//get the SavedGamesStats folder as a File
        File[] listOfFiles = folder.listFiles();//get all the files inside that folder, and stores it in an array of File type

        //need to sort the File[] array


        for (int i = 0; i < listOfFiles.length; i++) {
            String jsonString="";
            //System.out.println("File " + listOfFiles[i].getName());//display the file name:debugging purposes

            try {
                File tempFile = listOfFiles[i];
                BufferedReader buff = new BufferedReader(new FileReader(tempFile));
                jsonString = buff.readLine();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            //System.out.println(jsonString);
            SaveCreationHelper getTheGameStats = gson.fromJson(jsonString, SaveCreationHelper.class);
            listOfCreations.add(getTheGameStats);
        }

    }

    public ObservableList<SavedCreationObservable> getObervableSavedCreations (){

        for (int i = 0 ; i < listOfCreations.size() ; i++){
            SaveCreationHelper temp = listOfCreations.get(i);
            SavedCreationObservable converted = new SavedCreationObservable(temp.getCustomEquations() , temp.getCreationName() , temp.getTheDate() , temp.getTheTime() , temp.getUnixTimeStamp());

            observableArrayList.add(converted);//add the SaveGameObservable object to the observableArrayList

        }

        return observableArrayList;
    }



}
