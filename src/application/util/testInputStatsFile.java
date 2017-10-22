package application.util;

import java.util.ArrayList;

/**
 * this calss is solely used for testing the class: InputStatsFile
 *
 */
public class testInputStatsFile {

    public static void main(String[] args){
        InputStatsFile input = new InputStatsFile();
        input.getFiles();
        ArrayList<SaveGame> test = new ArrayList<>();
        test = input.getAllSavedGames();

        for (int i = 0; i < test.size(); i++) {
           SaveGame sg= test.get(i);
           System.out.print(sg.getUnixTimeStamp() + " ");
           System.out.println("the score was: " + sg.getTheScore());

        }
    }
}
