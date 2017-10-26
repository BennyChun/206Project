package application.util;

import application.model.EquationQuestion;
import com.google.gson.Gson;
import javafx.collections.ObservableList;

/**
 * this class is solely for testing the class: SaveGame
 * //this class is not used for the main app
 *
 * EDIT:
 * this class now can be used by the LevelScreenController
 * it takes an observable list, and converts it to a SaveGame class (so we can us json on it)
 * it takes a String (selectedLevel)
 * it takes a String (selectedOperation)
 *
 */
public class SaveGameHelper {

    public SaveGameHelper(ObservableList<EquationQuestion> LOL , String selectedLevel , String selectedOperation , int theScore , int points){

        //----------------------------------------------------------------------------------------------------
        SaveGame sg = new SaveGame(LOL, selectedLevel , selectedOperation , theScore , points);

        Gson gson = new Gson();
        String jsonString = gson.toJson(sg);//stores the observablelist into a json String
        System.out.println(jsonString);


        //need to write the string to a txt file
        //need to save that file to a folder, name that folder (SavedGames folder)
        //========================================================================================================
        String savedGamesDir = System.getProperty("user.dir")+"/SavedGamesStats/";

        OutputStatsFile out = new OutputStatsFile(savedGamesDir , sg.getUnixTimeStamp(), jsonString);

        //---------------------------------------------------------------------------------------------------------------
        //SaveGame getTheGame = gson.fromJson(str, SaveGame.class); //this will get the string, and will try and deserialize it to the SaveGame class.


    }
}
