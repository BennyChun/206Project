package application.util;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * this class should read all the txt files in a specified location/path
 * it should tak
 */
public class InputStatsFile {
    private String savedGamesDir = System.getProperty("user.dir")+"/SavedGamesStats/";
    private ArrayList<SaveGame> listOfSavedGames = new ArrayList<>();                   //this stores all the files as SaveGame object
    private Gson gson = new Gson();

    private ObservableList<SaveGameObservable> observableArrayList = FXCollections.observableArrayList();//stores all the observable lists of saveGame


    /**
     *
     */
    public void getFiles(){
        File folder = new File(savedGamesDir);//get the SavedGamesStats folder as a File
        File[] listOfFiles = folder.listFiles();//get all the files inside that folder, and stores it in an array of File type

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
            SaveGame getTheGameStats = gson.fromJson(jsonString, SaveGame.class);
            listOfSavedGames.add(getTheGameStats);
        }

    }

    /**
     * this getter method returns the arraylist of all the stats of saved games
     * the object in the array list is the SaveGame object.
     * please refer to SaveGame object for getters.
     *
     * will return NULL if there were NO filesS in the SavedGamesStats folder !!!!
     * @return
     */
    public ArrayList<SaveGame> getAllSavedGames(){
        return listOfSavedGames;
    }

    /**
     * should return the BbservableList<SaveGame> of the ArrayList<SaveGame>
     * @return
     */
    public ObservableList<SaveGameObservable> getObservableList(String selectedLevel){

        //loop through the ArrayList of SaveGame
        System.out.println(listOfSavedGames.size());
        for (int i = 0 ; i < listOfSavedGames.size() ; i++){
            //convert the SaveGame object to a SaveGameObservable object
            SaveGameObservable temp = new SaveGameObservable(listOfSavedGames.get(i));
            if (temp.getTheLevel().equals(selectedLevel)){
                observableArrayList.add(temp);//add the SaveGameObservable object to the observableArrayList

            }
        }

        return observableArrayList;
    }
}
