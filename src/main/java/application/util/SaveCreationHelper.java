package application.util;

import application.model.EquationQuestion;
import application.model.EquationQuestionAdapter;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * this class is used to help take in an ObservableList<EquationQuestion>
 * takes all every EquationQuestion
 * and convert it using the adapter class EquationQuestionAdapter
 * and place them into
 */
public class SaveCreationHelper {
    private ArrayList<EquationQuestionAdapter> customEquations = new ArrayList<>();
    private String creationName;
    private String theDate;
    private String theTime;
    private Long unixTimeStamp;


    public SaveCreationHelper (ObservableList<EquationQuestion> theTenEquations , String creationName){
        this.creationName = creationName;
        setDate();

        for (int i = 0 ; i < 10 ; i ++){
            EquationQuestion tempEquation = theTenEquations.get(i);
            EquationQuestionAdapter tempConverted = new EquationQuestionAdapter(tempEquation);
            customEquations.add(tempConverted);//add the converted EquationQuestion

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

    //===========================================================================================
    //                                  getters and setters
    //===========================================================================================
    public ArrayList<EquationQuestionAdapter> getCustomEquations() {
        return customEquations;
    }

    public String getCreationName() {
        return creationName;
    }

    public String getTheDate() {
        return theDate;
    }

    public String getTheTime() {
        return theTime;
    }

    public Long getUnixTimeStamp() {
        return unixTimeStamp;
    }
}
