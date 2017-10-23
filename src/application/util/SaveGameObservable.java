package application.util;

import javafx.beans.property.*;

import java.util.ArrayList;

/**
 * this calss acts like a wrapper class for: SaveGame
 * it will take in a SaveGame object
 * and it will convert all of the fields in the SaveGame
 * to Properties> so that we can use this class in an observable list
 *
 * WAIT: you might not need to make this new class
 * just go into the SaveGame class
 * and add getters which returns new SimpleIntegerProperty.....................WTF
 */
public class SaveGameObservable {
    private StringProperty theDate;     //records the date
    private StringProperty theTime;     //records the time
    private IntegerProperty theScore;       //records the score


    private StringProperty theLevel;    //records the level played
    private StringProperty theOperation;//records the operation used

    private LongProperty unixTimeStamp;  //reocords the unix time stamp, will use this as the txt file name to store the json string


    private int[] scoreArray = {0,0,0,0,0,0,0,0,0,0};//this helps keep track of which question they got correct and wrong
    private ArrayList<String> equationList = new ArrayList<>();         // this helps keep track of all the equations
    private ArrayList<Integer> answerList = new ArrayList<>();          //this helps keep track of all the answers (int)
    private ArrayList<Integer> attemptsList = new ArrayList<>();         //this helps stores the number of attempts

    //======================================================================================

    public SaveGameObservable(SaveGame theSavedGame){
        theDate = new SimpleStringProperty(theSavedGame.getTheDate());
        theTime = new SimpleStringProperty(theSavedGame.getTheTime());
        theScore = new SimpleIntegerProperty(theSavedGame.getTheScore());
        theLevel = new SimpleStringProperty(theSavedGame.getTheLevel());
        theOperation = new SimpleStringProperty(theSavedGame.getTheOperation());
        unixTimeStamp = new SimpleLongProperty(theSavedGame.getUnixTimeStamp());

        scoreArray = theSavedGame.getScoreArray();
        equationList = theSavedGame.getEquationList();
        answerList = theSavedGame.getAnswerList();
        attemptsList = theSavedGame.getAttemptsList();

    }
    //======================================================================================
    //                                     setters and getters
    //======================================================================================
    public String getTheDate() {
        return theDate.get();
    }

    public StringProperty theDateProperty() {
        return theDate;
    }
    //--------------------------------------------------------------
    public String getTheTime() {
        return theTime.get();
    }

    public StringProperty theTimeProperty() {
        return theTime;
    }
    //--------------------------------------------------------------
    public int getTheScore() {
        return theScore.get();
    }

    public IntegerProperty theScoreProperty() {
        return theScore;
    }
    //--------------------------------------------------------------
    public String getTheLevel() {
        return theLevel.get();
    }

    public StringProperty theLevelProperty() {
        return theLevel;
    }
    //--------------------------------------------------------------
    public String getTheOperation() {
        return theOperation.get();
    }

    public StringProperty theOperationProperty() {
        return theOperation;
    }

    //--------------------------------------------------------------
    public long getUnixTimeStamp() {
        return unixTimeStamp.get();
    }

    public LongProperty unixTimeStampProperty() {
        return unixTimeStamp;
    }

    //--------------------------------------------------------------
    public int[] getScoreArray() {
        return scoreArray;
    }

    public ArrayList<String> getEquationList() {
        return equationList;
    }

    public ArrayList<Integer> getAnswerList() {
        return answerList;
    }

    public ArrayList<Integer> getAttemptsList() {
        return attemptsList;
    }

}
