package application.util;

import application.model.EquationQuestion;
import com.google.gson.Gson;

/**
 * this class will help save a finished game
 * by taking in the 10 equationsQuestions
 */
public class SaveGame {

    public static void main(String[] args){


        Gson gson = new Gson();
        EquationQuestion temp = new EquationQuestion("easy" , "+");
        String str = gson.toJson(temp);
        System.out.println(str);



        //EquationQuestion q2 = gson.fromJson(str, EquationQuestion.class);
    }



}
