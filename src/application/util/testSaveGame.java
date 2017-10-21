package application.util;

import application.model.EquationQuestion;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * this class is solely for testing the class: SaveGame
 * //this class is not used for the main app
 */
public class testSaveGame {
    public static void main(String[] args){

        ObservableList<EquationQuestion> LOL = FXCollections.observableArrayList();
        for (int i = 0 ; i < 10 ; i++) {
            EquationQuestion temp = new EquationQuestion("easy", "+");
            LOL.add(temp);
        }


        SaveGame sg = new SaveGame(LOL, "easy" , "+" , 10);

        Gson gson = new Gson();
        String str = gson.toJson(sg);//stores the observablelist into a json String
        System.out.println(str);

        SaveGame getTheGame = gson.fromJson(str, SaveGame.class); //it worked ????


    }
}
