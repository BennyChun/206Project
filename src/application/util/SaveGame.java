package application.util;

import application.model.EquationQuestion;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * this class will help save a finished game
 * should be able to take a list of EquationQuestions
 * should also take a String of the level that was played
 * Should be able to take the Operation that was used
 *
 * should i save the whole list of 10 questions ???
 * dont i just want to save the [ score, the level , the operation, and maybe the time it was played ] ?
 */
public class SaveGame {

    private String theDate;     //records the date
    private String theTime;     //records the time
    private int theScore;       //records the score
    private String theLevel;    //records the level played
    private String theOperation;//records the operation used

    private long unixTimeStamp;  //reocords the unix time stamp, will use this as the txt file name to store the json string

    private int[] scoreArray = {0,0,0,0,0,0,0,0,0,0};//this helps keep track of which question they got correct and wrong
    private ArrayList<String> equationList = new ArrayList<>();         // this helps keep track of all the equations
    private ArrayList<Integer> answerList = new ArrayList<>();          //this helps keep track of all the answers (int)
    private ArrayList<Integer> attempsList = new ArrayList<>();         //this helps stores the number of attempts

//===============================================================================================================
    public SaveGame(ObservableList<EquationQuestion> theList , String theLevel , String theOperation, int theScore){

        setDate();//this method will help get the date, and set it as global fields

        this.theLevel = theLevel;
        this.theOperation = theOperation;
        this.theScore = theScore;


        for (int i  = 0 ; i < 10 ; i++){
            EquationQuestion tempEquation = theList.get(i);//gets the EquationQuestion
            answerList.add(tempEquation.getTheAnswer());   //stores the answer to the equation in a list
            equationList.add(tempEquation.getTheEquation());//stores the equation to the equationList;
            attempsList.add(tempEquation.getCurrentAttempts());//stores the number of attempts used on that equation question
            if (tempEquation.isCorrect()){
                //if the question got answered correctly
                scoreArray[i] = 1;//set it as 1, which means they got it correct, otherwise just leave it as 0 (default)
            }
        }
    }


    /**
     * this method sets the current date and time
     * and stores it as a global field: theDate , theTime.
     */
    private void setDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        theDate = dateFormat.format(date);
        unixTimeStamp = date.getTime();//this gets the unix time stamps
        theTime = dateFormatTime.format(date);
    }

    //===========================================================================================================
    //                                            getters nad setters
    //===========================================================================================================

    /**
     * this method returns the unix time stamp
     * @return
     */
    public long getUnixTimeStamp(){
        return unixTimeStamp;
    }


}
